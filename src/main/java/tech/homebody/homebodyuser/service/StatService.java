package tech.homebody.homebodyuser.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;
import org.threeten.bp.Duration;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.format.DateTimeFormatter;
import tech.homebody.homebodyuser.dto.RecordDto;
import tech.homebody.homebodyuser.dto.StatDto;
import tech.homebody.homebodyuser.dto.TotalStatDto;
import tech.homebody.homebodyuser.entity.ExerciseHistory;
import tech.homebody.homebodyuser.entity.PoseInfo;
import tech.homebody.homebodyuser.repository.ExerciseHistoryRepository;
import tech.homebody.homebodyuser.repository.PoseInfoRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class StatService {
    private PoseInfoRepository poseInfoRepository;
    private ExerciseHistoryRepository exerciseHistoryRepository;
    private final String UPPER = "upper";
    private final String LOWER = "lower";
    private final String FULL = "full";
    private final String TOTAL = "total";
    private final String WEEK = "week";
    private final String MONTH = "month";
    private final String YEAR = "year";

    public StatService(PoseInfoRepository poseInfoRepository, ExerciseHistoryRepository exerciseHistoryRepository) {
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

    public TotalStatDto getMyExerciseStat(String userId, LocalDate start_period, LocalDate end_period, Double weight) {
        List<ExerciseHistory> exerciseHistories = exerciseHistoryRepository.findExerciseHistoryByUserIdAndPeriod(userId, start_period, end_period);
        // 기간 설정 들어가야함.
        StatDto upper = new StatDto(0L,0d, 0);
        StatDto lower = new StatDto(0L,0d, 0);
        StatDto full = new StatDto(0L,0d, 0);

        Double mets = 0.03 * weight;

        for(ExerciseHistory exerciseHistoryDto: exerciseHistories) {
            log.info(exerciseHistoryDto.getStartTime());

            RecordDto record = jsonStringToRecordsList(exerciseHistoryDto.getRecords());
            if (record != null) {
                PoseInfo exercise_info = poseInfoRepository.findByName(record.getName());


                LocalDateTime start_exercise = LocalDateTime.parse(exerciseHistoryDto.getStartTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                LocalDateTime end_exercise = LocalDateTime.parse(exerciseHistoryDto.getEndTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                String exerciseType = poseInfoRepository.findByName(record.getName()).getType();
                int exerciseAverageTimeOfOneRep = poseInfoRepository.findByName(record.getName()).getAverageTimeOfOneRep();
                double num = (exerciseType.equals("AI")) ? record.getValue() : (exerciseType.equals("TIME-COUNT")) ? Math.min(Math.ceil(record.getValue() / (exerciseAverageTimeOfOneRep / 2)), record.getGoal() / exerciseAverageTimeOfOneRep) :
                        Math.ceil(record.getValue() / 5);
                int exercise_num = (int) num;

                String exercisePart = exercise_info.getPart(); //upper, lower, full
                switch (exercisePart) {
                    case "upper":
                        upper.add(Duration.between(start_exercise, end_exercise).getSeconds());
                        upper.add(exercise_num);
                        break;
                    case "lower":
                        lower.add(Duration.between(start_exercise, end_exercise).getSeconds());
                        upper.add(exercise_num);
                        break;
                    case "full":
                        full.add(Duration.between(start_exercise, end_exercise).getSeconds());
                        upper.add(exercise_num);
                        break;
                }
            }
        }

        upper.add(mets * upper.getSecond() / 60);
        lower.add(mets * lower.getSecond() / 60);
        full.add(mets * full.getSecond() / 60);
        StatDto total = new StatDto(upper.getSecond()+lower.getSecond()+full.getSecond(),upper.getCalorie()+lower.getCalorie()+ full.getCalorie(), upper.getExercise_num()+lower.getExercise_num()+full.getExercise_num());
        log.info(String.valueOf(upper));
        log.info(String.valueOf(lower));
        log.info(String.valueOf(full));
        log.info(String.valueOf(total));

        return new TotalStatDto(upper,lower,full,total);
    }

}
