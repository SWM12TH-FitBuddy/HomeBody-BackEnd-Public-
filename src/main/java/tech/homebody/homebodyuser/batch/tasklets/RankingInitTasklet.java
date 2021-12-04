package tech.homebody.homebodyuser.batch.tasklets;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Local;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.threeten.bp.LocalDate;

@Slf4j
public class RankingInitTasklet implements Tasklet {
    private final StringRedisTemplate redisTemplate;
    private final String[] PART = {"upper", "lower", "full", "total"};

    public RankingInitTasklet(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        LocalDate now = LocalDate.now(); // 2021-10-20
        //LocalDate earlier = now.minusMonths(1); // 2021-09-20
        //String lastMonth = earlier.toString().split("-")[0] + earlier.toString().split("-")[1] + ":"; // 202109:

        String lastYear = now.minusYears(1).toString().split("-")[0]+":"; //2020-11-01


        for(String part : PART) {
            try {
                String key = lastYear + part;
                if (Boolean.TRUE.equals(redisTemplate.hasKey(key))) {
                    try {
                        redisTemplate.delete(key);
                        log.info(key + " deleted");
                    } catch (NullPointerException nullE) {
                        log.info(key + " null");
                    }
                }
            } catch (Exception e) {
                log.info("redis doesn't have a key");
                e.printStackTrace();
            }
        }
        log.info("monthly init");
        return RepeatStatus.FINISHED;
    }
}