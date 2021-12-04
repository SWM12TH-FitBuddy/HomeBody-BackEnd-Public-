package tech.homebody.homebodyuser.repository.custom;

import org.threeten.bp.LocalDate;
import tech.homebody.homebodyuser.entity.ExerciseHistory;

import java.util.List;

public interface CustomExerciseHistoryRepository {
    List<ExerciseHistory> findThisMonthExerciseHistoryByUserId(String userId);
    List<ExerciseHistory> findExerciseHistoryByUserIdAndPeriod(String userId, LocalDate start_period, LocalDate end_period);
}
