package tech.homebody.homebodyuser.service;

import org.springframework.stereotype.Service;
import tech.homebody.homebodyuser.dto.ExerciseHistoryDto;
import tech.homebody.homebodyuser.repository.ExerciseHistoryRepository;

@Service
public class HistoryService {
    private ExerciseHistoryRepository exerciseHistoryRepository;

    public HistoryService(ExerciseHistoryRepository exerciseHistoryRepository) {
        this.exerciseHistoryRepository = exerciseHistoryRepository;
    }

    public void addHistory(ExerciseHistoryDto exerciseHistoryDto){
        exerciseHistoryRepository.save(exerciseHistoryDto.toEntity());
    }
}
