package tech.homebody.homebodyuser.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Schema(name = "ExerciseHistory", description = "운동 기록")
public class ExerciseHistory {

    @Schema(required = true, example = "id", description = "운동 히스토리 id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // mysql AUTO_INCREMENT
    private Long idx;

    @Schema(example = "user_id", description = "유저 id")
    @Column(nullable = false)
    private String userId;

    @Schema(example = "exercise_id", description = "단일운동 또는 루틴 id")
    @Column
    private String type;

    @Schema(example = "1 day", description = "루틴 운동일 경우 day")
    @Column
    private Integer dayOfRoutine;

    @Schema(example = "{name : squat, value : 10}", description = "운동 기록")
    @Column
    private String records;

    @Schema(example = "complete", description = "모두 완료시 complete, 중도 포기 - 어려운 경우 hard, 쉬운 경우 easy")
    @Column
    private String level;

    @Schema(example = "2021-09-27 16:04", description = "운동 시작 시간")
    @Column
    private String startTime;

    @Schema(example = "2021-09-27 16:05", description = "운동 종료 시간")
    @Column
    private String endTime;

    @Builder
    public ExerciseHistory(Long idx, String userId, String type, Integer dayOfRoutine, String records, String level, String startTime, String endTime) {
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
