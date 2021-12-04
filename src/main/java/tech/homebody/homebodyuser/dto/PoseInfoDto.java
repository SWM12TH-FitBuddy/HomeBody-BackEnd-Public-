package tech.homebody.homebodyuser.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PoseInfoDto {
    private String name;
    private String part;
    private String type;

}
