package tech.homebody.homebodyuser.batch.jobs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import tech.homebody.homebodyuser.batch.tasklets.RankingInitTasklet;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class RankingConfig {
    private final JobBuilderFactory jobBuilderFactory; // Job 빌더 생성용
    private final StepBuilderFactory stepBuilderFactory; // Step 빌더 생성용
    private final StringRedisTemplate stringRedisTemplate;

    // JobBuilderFactory 통해서 rankingJob 생성
    @Bean
    public Job rankingJob() {
        return jobBuilderFactory.get("rankingJob").start(rankingInitStep()).build(); //step 설정
    }

    // StepBuilderFactory 통해서 rankingStep 생성
    @Bean
    public Step rankingInitStep() {
        return stepBuilderFactory.get("rankingInitStep").tasklet(new RankingInitTasklet(stringRedisTemplate)).build(); //Tasklet 설정

    }
}