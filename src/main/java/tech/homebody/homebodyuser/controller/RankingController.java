package tech.homebody.homebodyuser.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import tech.homebody.homebodyuser.dto.ExerciseHistoryDto;
import tech.homebody.homebodyuser.dto.RankingUserInfoDto;
import tech.homebody.homebodyuser.dto.security.User;
import tech.homebody.homebodyuser.service.HistoryService;
import tech.homebody.homebodyuser.service.RedisService;

import java.util.ArrayList;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@Tag(name = "랭킹 컨트롤러", description = "랭킹 API 문서")
public class RankingController {

    private final HistoryService historyService;
    private final RedisService redisService;
    
    public RankingController(HistoryService historyService, RedisService redisService){
        this.historyService = historyService;
        this.redisService = redisService;
    }

    @Operation(security = { @SecurityRequirement(name = "bearer-key") }, summary = "운동 기록 저장되면서 랭킹에 점수 반영되는 api", tags = {"record"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200")
    })
    @PostMapping("/record")
    public ResponseEntity<Object> saveHistory(@RequestBody ExerciseHistoryDto exerciseHistoryDto, @AuthenticationPrincipal User user) {
        exerciseHistoryDto.setUserId(user.getUid());
        log.info(exerciseHistoryDto.toString());
        historyService.addHistory(exerciseHistoryDto);
        redisService.addScore(exerciseHistoryDto);
        return ResponseEntity.ok().build();
    }
    
    @Operation(summary = "랭킹 1~50등 정보 가져오는 api", tags = {"ranking"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = RankingUserInfoDto.class))))
    })
    @GetMapping("/ranking/top50/{part}")
    public ArrayList<RankingUserInfoDto> getTop50Ranking(@Parameter(description = "lower / upper / full / total 중 택 1") @PathVariable("part") String part){
        return redisService.deliveryTop50(part);
    }

    @Operation(security = { @SecurityRequirement(name = "bearer-key") }, summary = "내 랭킹을 가져오는 api", tags = {"ranking"})
    @GetMapping("/ranking/myRanking/{part}")
    public Map<String, Object> getMyRanking(@Parameter(description = "lower / upper / full / total 중 택 1") @PathVariable("part") String part, @AuthenticationPrincipal User user){
        return redisService.myRankInPart(user.getUid(), part);
    }

    @Operation(security = { @SecurityRequirement(name = "bearer-key") }, summary = "유저 정보를 가져오는 api", tags = {"ranking"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = User.class))))
    })
    @GetMapping("/user-details")
    public ResponseEntity<User> getUserInfo(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(user);
    }


    @GetMapping("/hello")
    public String hello() {
        return "hello!";
    }

    @GetMapping("/redis")
    public String redis() {
        redisService.addScore();
        return "통합 완료";
    }
}
