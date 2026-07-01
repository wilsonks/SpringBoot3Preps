package org.wilsonks.road_map.micro_services.url_shortner_service.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wilsonks.road_map.micro_services.url_shortner_service.service.UrlShortnerService;

import java.util.Map;

@RestController
@RequestMapping("/api/url")
@AllArgsConstructor
public class UrlShortnerController {

    private UrlShortnerService service;


    @PostMapping("/shorten")
    public ResponseEntity<?> shortenUrl(@RequestBody Map<String, String> request) {
        String originalUrl = request.get("originalUrl");
        String shortenedUrl =  service.shortnerUrl(originalUrl);

        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("shortenedUrl", shortenedUrl));
    }
}
