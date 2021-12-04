package tech.homebody.homebodyuser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.homebody.homebodyuser.entity.ExerciseHistory;
import tech.homebody.homebodyuser.repository.custom.CustomExerciseHistoryRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ExerciseHistoryRepository extends JpaRepository<ExerciseHistory, Long>, CustomExerciseHistoryRepository {
    List<ExerciseHistory> findByUserId(String userId);
    List<ExerciseHistory> findAllByStartTimeBetween(LocalDateTime start, LocalDateTime end);
}