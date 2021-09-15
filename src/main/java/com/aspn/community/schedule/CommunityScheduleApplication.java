package com.aspn.community.schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.aspn.community.schedule")
@EnableScheduling
public class CommunityScheduleApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommunityScheduleApplication.class, args);
    }
}
