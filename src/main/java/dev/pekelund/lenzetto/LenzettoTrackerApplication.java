package dev.pekelund.lenzetto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableConfigurationProperties(TrackerProperties.class)
public class LenzettoTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LenzettoTrackerApplication.class, args);
    }
}
