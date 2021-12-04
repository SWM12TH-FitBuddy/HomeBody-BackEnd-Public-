package tech.homebody.homebodyuser.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import tech.homebody.homebodyuser.dto.security.SecurityProperties;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Configuration
public class FirebaseConfig {

    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    SecurityProperties secProps;

    @Primary
    @Bean
    public void firebaseInit() {
        try {
            Resource resource = resourceLoader.getResource("classpath:service-account-key.json");
            InputStream input = resource.getInputStream();

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(input))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                log.info("firebaseApp init successful");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
