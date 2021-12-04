package tech.homebody.homebodyuser.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.Iterator;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RankingUserInfoDto {

    @Schema(example = "211.00", description = "1~50위 점수")
    private Double score;

    @Schema(example = "https://lh3.googleusercontent.com/a/AATXAJxCeHvGFo6YEkjhW6WQTgjxmmfFOnm7yQUaM1WR=s96-c", description = "유저 이미지 url")
    private String imageLink;

    @Schema(example = "FitBuddy", description = "유저가 설정한 이름")
    private String nickName;

}
