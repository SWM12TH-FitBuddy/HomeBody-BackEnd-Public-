package tech.homebody.homebodyuser.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class TotalStatDto {
    private StatDto upper;
    private StatDto lower;
    private StatDto full;
    private StatDto total;
}
