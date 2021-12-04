package tech.homebody.homebodyuser.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordDto {
    private String name;
    private Integer value;
    private Integer goal;
}
