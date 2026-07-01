package org.wilsonks.road_map.micro_services.url_shortner_service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class UrlShortnerApplicationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testShortenUrl() {
        String originalUrl = "https://www.example.com";
        Map<String, String> request = Map.of("originalUrl", originalUrl);

        ResponseEntity<String> response = restTemplate.postForEntity("/api/url/shorten", request, String.class);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        log.info("Shortened URL: {}", response.getBody());

    }


}