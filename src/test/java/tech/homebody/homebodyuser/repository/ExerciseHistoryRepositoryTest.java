package tech.homebody.homebodyuser.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.homebody.homebodyuser.HomeBodyUserApplicationTests;
import tech.homebody.homebodyuser.entity.ExerciseHistory;

@Slf4j
public class ExerciseHistoryRepositoryTest extends HomeBodyUserApplicationTests {
    @Autowired
    private ExerciseHistoryRepository exerciseHistoryRepository;

    @Test
    public void create() {
        ExerciseHistory exerciseHistoryLee_1 =
                ExerciseHistory.builder().type("single1").records(
                        "[{\"name" +
                                "\":\"squat\", \"value\":10}, {\"name\":\"lunge\", " +
                                "\"value\":10}]").level("complete").startTime("2021" +
                        "/09/12 17:34:23").endTime("2021/09/12 17:35:01").build();

        //insert
        exerciseHistoryRepository.save(exerciseHistoryLee_1);
        log.info(exerciseHistoryLee_1.toString());
    }

    @Test
    public void update() {
        ExerciseHistory exerciseHistoryLee_1 =
                ExerciseHistory.builder().type("single1").records(
                        "[{\"name" +
                                "\":\"squat\", \"value\":10}, {\"name\":\"lunge\", " +
                                "\"value\":10}]").level("complete").startTime("2021" +
                        "/09/13 07:30:14").endTime("2021/09/13 07:32:17").build();
    }
}
