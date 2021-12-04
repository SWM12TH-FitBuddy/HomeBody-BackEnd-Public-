package tech.homebody.homebodyuser.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.threeten.bp.LocalDate;
import tech.homebody.homebodyuser.dto.ExerciseHistoryDto;
import tech.homebody.homebodyuser.dto.RecordDto;
import tech.homebody.homebodyuser.dto.RankingUserInfoDto;
import tech.homebody.homebodyuser.entity.ExerciseHistory;
import tech.homebody.homebodyuser.repository.ExerciseHistoryRepository;
import tech.homebody.homebodyuser.repository.PoseInfoRepository;

import java.util.*;

@Slf4j
@Service
public class RedisService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    private PoseInfoRepository poseInfoRepository;
    private ExerciseHistoryRepository exerciseHistoryRepository;
    private final String UPPER = "upper";
    private final String LOWER = "lower";
    private final String FULL = "full";
    private final String TOTAL = "total";

    public RedisService(PoseInfoRepository poseInfoRepository, ExerciseHistoryRepository exerciseHistoryRepository) {
        this.poseInfoRepository = poseInfoRepository;
        this.exerciseHistoryRepository = exerciseHistoryRepository;
    }

    public RecordDto jsonStringToRecordsList(String jsonString){
        RecordDto records = null;
        ObjectMapper objectMapper = new ObjectMapper();
        log.info(jsonString);
        try {
            records = objectMapper.readValue(jsonString, new TypeReference<>() {});
        } catch (Exception e){
            log.debug(e.getMessage());
        }
        return records;

    }

    public void addScore(){
        List<ExerciseHistory> exerciseHistorys = exerciseHistoryRepository.findAll();
        for(ExerciseHistory exerciseHistoryDto: exerciseHistorys) {
                String record_String = exerciseHistoryDto.getRecords();
                String[] records = record_String.split(",");
                String exercisePart;
                Double score;
                if (records.length == 3) {
                    RecordDto record = new RecordDto(records[0].split(": ")[1], Integer.parseInt(records[1].split(": ")[1]), Integer.parseInt(records[2].split(": ")[1].split("}")[0]));
                    log.info(String.valueOf(record));
                    exercisePart = poseInfoRepository.findByName(record.getName()).getPart();
                    String exerciseType = poseInfoRepository.findByName(record.getName()).getType();
                    int exerciseAverageTimeOfOneRep = poseInfoRepository.findByName(record.getName()).getAverageTimeOfOneRep();
                    log.info(String.valueOf(exerciseHistoryDto));
                    score = (exerciseType.equals("AI")) ? record.getValue() * 20.0 : (exerciseType.equals("TIME-COUNT")) ? Math.min(record.getValue() / (exerciseAverageTimeOfOneRep / 2) * 10, record.getGoal() / exerciseAverageTimeOfOneRep * 10) :
                            record.getValue() * 2;
                    String yearMonthDay = exerciseHistoryDto.getStartTime().split(" ")[0]; //2021-10-17
                    String thisYearMonth = yearMonthDay.split("-")[0] + ":"; //2021:
                    switch (exercisePart) {
                        case UPPER:
                            insertIntoRedis(thisYearMonth + UPPER, exerciseHistoryDto.getUserId(), score);
                            break;
                        case LOWER:
                            insertIntoRedis(thisYearMonth + LOWER, exerciseHistoryDto.getUserId(), score);
                            break;
                        case FULL:
                            insertIntoRedis(thisYearMonth + FULL, exerciseHistoryDto.getUserId(), score);
                            break;
                    }
                    insertIntoRedis(thisYearMonth + TOTAL, exerciseHistoryDto.getUserId(), score);
                }
            }
    }

    public void addScore(ExerciseHistoryDto exerciseHistoryDto){
        RecordDto record;
        record = jsonStringToRecordsList(exerciseHistoryDto.getRecords());
        String exercisePart = poseInfoRepository.findByName(record.getName()).getPart();
        String exerciseType = poseInfoRepository.findByName(record.getName()).getType();
        int exerciseAverageTimeOfOneRep = poseInfoRepository.findByName(record.getName()).getAverageTimeOfOneRep();
        Double score = (exerciseType.equals("AI")) ? record.getValue() * 20.0 : (exerciseType.equals("TIME-COUNT")) ? record.getValue()/(exerciseAverageTimeOfOneRep/2) * 10 :
                record.getValue() * 2;
        String yearMonthDay = exerciseHistoryDto.getStartTime().split(" ")[0]; //2021-10-17
        String thisYearMonth = yearMonthDay.split("-")[0] + ":"; //202110:
        switch (exercisePart) {
            case UPPER:
                insertIntoRedis(thisYearMonth+UPPER, exerciseHistoryDto.getUserId(), score);
                break;
            case LOWER:
                insertIntoRedis(thisYearMonth+LOWER, exerciseHistoryDto.getUserId(), score);
                break;
            case FULL:
                insertIntoRedis(thisYearMonth+FULL, exerciseHistoryDto.getUserId(), score);
                break;
        }
        insertIntoRedis(thisYearMonth+TOTAL, exerciseHistoryDto.getUserId(), score);
    }

    public void insertIntoRedis(String key, String userId, Double score) {
        ZSetOperations<String, String> stringStringZSetOperations = redisTemplate.opsForZSet();
        stringStringZSetOperations.incrementScore(key, userId, score);
    }  

    public ArrayList<RankingUserInfoDto> deliveryTop50(String key){
        LocalDate now = LocalDate.now();
        String thisMonth = now.toString().split("-")[0] +  ":";
//        String thisMonth = now.toString().split("-")[0] + now.toString().split("-")[1] + ":";
        return getTop50Rank(thisMonth+key);
    }

    public ArrayList<RankingUserInfoDto> getTop50Rank(String key) {
        ZSetOperations<String, String> stringStringZSetOperations = redisTemplate.opsForZSet();
        Set<ZSetOperations.TypedTuple<String>> scoreRange =
                stringStringZSetOperations.reverseRangeWithScores(key, 0, 49);

        ArrayList<RankingUserInfoDto> top50Rank = new ArrayList<>();

        if (scoreRange != null) {
            scoreRange.iterator().forEachRemaining(user -> {
                try {
                    RankingUserInfoDto userInfo = new RankingUserInfoDto();
                    userInfo.setScore(user.getScore());
                    UserRecord userRecord = FirebaseAuth.getInstance().getUser(user.getValue());
                    log.info("FIREBASE USER INFO >>>>>>>>>>>>>>>> name : " + userRecord.getDisplayName() +" photo : "+userRecord.getPhotoUrl());
                    userInfo.setNickName(userRecord.getDisplayName());
                    userInfo.setImageLink(userRecord.getPhotoUrl());
                    top50Rank.add(userInfo);
                } catch (FirebaseAuthException e) {
                    e.printStackTrace();
                }
            });
            return top50Rank;
        }
        return null;
    }


    public Map<String, Object> myRankInPart(String userId, String key) {
        ZSetOperations<String, String> stringStringZSetOperations = redisTemplate.opsForZSet();
        Map<String, Object> myRanking = new HashMap<>();
        LocalDate now = LocalDate.now();
//        String thisMonth = now.toString().split("-")[0] + now.toString().split("-")[1] + ":";
        String thisMonth = now.toString().split("-")[0] + ":";

        Integer myRank = Objects.requireNonNull(stringStringZSetOperations.reverseRank(thisMonth + key, userId)).intValue()+1;
        Double myScore = Objects.requireNonNull(stringStringZSetOperations.score(thisMonth + key, userId)).doubleValue();

        myRanking.put("myRank",myRank);
        myRanking.put("myScore",myScore);

        return myRanking;
    }

}
