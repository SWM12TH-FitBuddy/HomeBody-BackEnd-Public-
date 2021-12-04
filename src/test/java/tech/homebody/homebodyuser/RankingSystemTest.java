//package tech.homebody.homebodyuser;
//
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import tech.homebody.homebodyuser.dto.ExerciseHistoryDto;
//import tech.homebody.homebodyuser.entity.ExerciseHistory;
//import tech.homebody.homebodyuser.entity.PoseInfo;
//import tech.homebody.homebodyuser.repository.ExerciseHistoryRepository;
//import tech.homebody.homebodyuser.repository.PoseInfoRepository;
//import tech.homebody.homebodyuser.repository.custom.CustomExerciseHistoryRepository;
//import tech.homebody.homebodyuser.repository.custom.CustomExerciseHistoryRepositoryImpl;
//import tech.homebody.homebodyuser.service.HistoryService;
//import tech.homebody.homebodyuser.service.RankService;
//
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import java.util.ArrayList;
//import java.util.List;
//
//@Slf4j
//public class RankingSystemTest extends HomeBodyUserApplicationTests {
//    @Autowired
//    private HistoryService historyService;
//
//    @Autowired
//    //private RankService rankService;
//
//    @Autowired
//    private PoseInfoRepository poseInfoRepository;
//
//    @Test
//    public void poseInfoCreate() {
//        List<PoseInfo> poseInfoList = new ArrayList<>();
//        poseInfoList.add(new PoseInfo("squat", "lower", "AI"));
//        poseInfoList.add(new PoseInfo("pushup", "total", "AI"));
//        poseInfoList.add(new PoseInfo("lunge", "lower", "AI"));
//
//        poseInfoRepository.saveAll(poseInfoList);
//    }
//
//    @Test
//    public void addHistoryAndRank() {
//        ExerciseHistoryDto exerciseHistoryLee_1 =
//                ExerciseHistoryDto.builder().userId("lee").type("routine1").records(
//                        "[{\"name" +
//                        "\":\"squat\", \"value\":10}, {\"name\":\"lunge\", " +
//                        "\"value\":10}]").level("complete").startTime("2021" +
//                        "/09/12 17:34:23").endTime("2021/09/12 17:35:01").build();
//
//        ExerciseHistoryDto exerciseHistoryLee_2 =
//                ExerciseHistoryDto.builder().userId("lee").type("single1").records(
//                        "[{\"name" +
//                                "\":\"squat\", \"value\":10}]").level("complete").startTime("2021" +
//                        "/09/12 17:52:32").endTime("2021/09/12 17:53:28").build();
//
//        ExerciseHistoryDto exerciseHistoryLee_3 =
//                ExerciseHistoryDto.builder().userId("lee").type("routine3").records(
//                        "[{\"name" +
//                                "\":\"pushup\", \"value\":5}, " +
//                                "{\"name\":\"squat\", " +
//                                "\"value\":20}]").level("complete").startTime(
//                                        "2021" +
//                        "/09/12 19:32:44").endTime("2021/09/12 19:35:01").build();
//
//        ExerciseHistoryDto exerciseHistoryLee_4 =
//                ExerciseHistoryDto.builder().userId("lee").type("single2").records(
//                        "[{\"name\":\"lunge\", \"value\":10}]").level("complete").startTime("2021" +
//                        "/09/12 14:23:00").endTime("2021/09/12 14:23:52").build();
//
//        ExerciseHistoryDto exerciseHistoryLee_5 =
//                ExerciseHistoryDto.builder().userId("lee").type("routine1").records(
//                        "[{\"name" +
//                                "\":\"squat\", \"value\":10}, {\"name\":\"lunge\", " +
//                                "\"value\":10}]").level("complete").startTime("2021" +
//                        "/09/12 19:34:23").endTime("2021/09/12 19:35:01").build();
//
//
//        // insert
//        historyService.addHistory(exerciseHistoryLee_1);
//        rankService.updateRank(exerciseHistoryLee_1);
//
//        historyService.addHistory(exerciseHistoryLee_2);
//        rankService.updateRank(exerciseHistoryLee_2);
//
//        historyService.addHistory(exerciseHistoryLee_3);
//        rankService.updateRank(exerciseHistoryLee_3);
//
//        historyService.addHistory(exerciseHistoryLee_4);
//        rankService.updateRank(exerciseHistoryLee_4);
//
//        historyService.addHistory(exerciseHistoryLee_5);
//        rankService.updateRank(exerciseHistoryLee_5);
//
//        log.info(exerciseHistoryLee_5.toString());
//
//
//        ExerciseHistoryDto exerciseHistoryKoala_1 =
//                ExerciseHistoryDto.builder().userId("koala").type("single3").records(
//                "[{\"name\":\"squat\", \"value\":7}, {\"name\":\"lunge\", " +
//                        "\"value\":15}]").level("complete").startTime("2021" +
//                "/09/12 17:34:23").endTime("2021/09/12 17:35:01").build();
//        ExerciseHistoryDto exerciseHistoryKoala_2 = ExerciseHistoryDto.builder().userId("koala").type("single1").records(
//                "[{\"name\":\"squat\", \"value\":20}, {\"name\":\"pushup\", " +
//                        "\"value\":10}]").level("complete").startTime("2021" +
//                "/09/12 17:34:23").endTime("2021/09/12 17:35:01").build();
//        ExerciseHistoryDto exerciseHistoryKoala_3 = ExerciseHistoryDto.builder().userId("koala").type("single1").records(
//                "[{\"name\":\"pushup\", \"value\":10}, {\"name\":\"lunge\", " +
//                        "\"value\":10}]").level("complete").startTime("2021" +
//                "/09/12 17:34:23").endTime("2021/09/12 17:35:01").build();
//        ExerciseHistoryDto exerciseHistoryKoala_4 = ExerciseHistoryDto.builder().userId("koala").type("single1").records(
//                "[{\"name\":\"squat\", \"value\":10}]").level("complete").startTime("2021" +
//                "/09/12 17:34:23").endTime("2021/09/12 17:35:01").build();
//        ExerciseHistoryDto exerciseHistoryKoala_5 = ExerciseHistoryDto.builder().userId("koala").type("single1").records(
//                "[{\"name" +
//                        "\":\"squat\", \"value\":10}, {\"name\":\"lunge\", " +
//                        "\"value\":10}]").level("complete").startTime("2021" +
//                "/09/12 17:34:23").endTime("2021/09/12 17:35:01").build();
//
//
//        // insert
//        historyService.addHistory(exerciseHistoryKoala_1);
//        rankService.updateRank(exerciseHistoryKoala_1);
//
//        historyService.addHistory(exerciseHistoryKoala_2);
//        rankService.updateRank(exerciseHistoryKoala_2);
//
//        historyService.addHistory(exerciseHistoryKoala_3);
//        rankService.updateRank(exerciseHistoryKoala_3);
//
//        historyService.addHistory(exerciseHistoryKoala_4);
//        rankService.updateRank(exerciseHistoryKoala_4);
//
//        historyService.addHistory(exerciseHistoryKoala_5);
//        rankService.updateRank(exerciseHistoryKoala_5);
//
//        log.info(exerciseHistoryKoala_5.toString());
//    }
//
//}
