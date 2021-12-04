package tech.homebody.homebodyuser.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.homebody.homebodyuser.entity.ExerciseHistory;

@Data
@NoArgsConstructor
public class ExerciseHistoryDto {
    @Schema(example = "id", description = "request body에 포함시킬 필요 없는 value")
    private Long idx;

    @Schema(example = "user_id", description = "request body에 포함시킬 필요 없는 value")
    private String userId;

    @Schema(example = "exercise_id", description = "단일운동 또는 루틴 id")
    private String type;

    @Schema(example = "1", description = "루틴 운동일 경우 day")
    private Integer dayOfRoutine;

    @Schema(example = "[{\"name\" : \"squat\", \"value\" : 10}, {\"name\" : \"push_up\", \"value\" : 20}]",
            description = "운동 기록")
    private String records;

    @Schema(example = "complete", description = "모두 완료시 complete, 중도 포기 - 어려운 경우 hard, 쉬운 경우 easy")
    private String level;

    @Schema(example = "2021-09-27 16:04", description = "운동 시작 시간")
    private String startTime;

    @Schema(example = "2021-09-27 16:05", description = "운동 종료 시간")
    private String endTime;

    public ExerciseHistory toEntity() {
        return ExerciseHistory.builder().userId(userId).type(type).dayOfRoutine(dayOfRoutine).records(records).level(level).startTime(startTime).endTime(endTime).build();
    }

    @Builder
    public ExerciseHistoryDto(Long idx, String userId, String type, Integer dayOfRoutine, String records, String level, String startTime, String endTime) {
        this.idx = idx;
        this.userId = userId;
        this.type = type;
        this.dayOfRoutine = dayOfRoutine;
        this.records = records;
        this.level = level;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
