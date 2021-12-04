package tech.homebody.homebodyuser.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatDto {
    @Schema(example = "2500", description = "총 운동한 시간을 초 단위로 환산한 값")
    private Long second;

    @Schema(example = "211.56", description = "운동으로 뺀 칼로리")
    private Double calorie;
    
    @Schema(example = "211", description = "운동한 총 개수")
    private Integer exercise_num;

    public void add(Long data){
        this.second += data;
    }
    public void add(Double data){
        this.calorie += data;
    }
    public void add(Integer data){
        this.exercise_num += data;
    }
}
