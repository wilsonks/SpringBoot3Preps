package org.wilsonks.road_map.micro_services.url_shortner_service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class UrlShortnerApplication {
    public static void main(String[] args) {
        SpringApplication.run(UrlShortnerApplication.class, args);
        log.info("Url Shortner Service is running...");
    }
}
