package tech.homebody.homebodyuser.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;
import tech.homebody.homebodyuser.dto.TotalStatDto;
import tech.homebody.homebodyuser.dto.security.User;
import tech.homebody.homebodyuser.service.StatService;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@Tag(name = "유저 스탯(운동 지표) 컨트롤러", description = "랭킹 API 문서")
public class StatController {
    private final StatService statService;
    public StatController(StatService statService) {
        this.statService = statService;
    }

    @Operation(security = { @SecurityRequirement(name = "bearer-key") }, summary = "사용자의 운동 지표를 가져오는 api", tags = {"ranking"})
    @GetMapping("/stat/{start_period}/{end_period}/{weight}")
    public TotalStatDto getMyExerciseStat(@AuthenticationPrincipal User user, @Parameter(description = "yyyy-MM-dd")  @PathVariable("start_period") String start_period, @Parameter(description = "yyyy-MM-dd") @PathVariable("end_period") String end_period, @Parameter(description = "82.1") @PathVariable("weight") Double weight) {

        return statService.getMyExerciseStat(user.getUid(), LocalDate.parse(start_period, DateTimeFormatter.ISO_DATE), LocalDate.parse(end_period, DateTimeFormatter.ISO_DATE), weight);
    }

}
