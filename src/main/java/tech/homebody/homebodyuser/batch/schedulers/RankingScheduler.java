package tech.homebody.homebodyuser.batch.schedulers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class RankingScheduler {
    private final Job job; //rankingJob
    private final JobLauncher jobLauncher;

    @Scheduled(cron = "0 0 0 5 1 ?")
    public void executeJob() {
        try{
            jobLauncher.run(job, new JobParametersBuilder().addString("datetime", LocalDateTime.now().toString()).toJobParameters());
        } catch (JobExecutionException e) {
            log.info(e.getMessage());
            e.printStackTrace();
        }
    }
}