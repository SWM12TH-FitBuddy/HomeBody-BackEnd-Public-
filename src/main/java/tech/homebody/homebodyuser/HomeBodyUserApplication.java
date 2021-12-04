package tech.homebody.homebodyuser;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@OpenAPIDefinition(
        servers = @Server(url = "/", description = "Default Server URL")
)
@EnableScheduling
@EnableBatchProcessing
@SpringBootApplication
public class HomeBodyUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(HomeBodyUserApplication.class, args);
    }
}
