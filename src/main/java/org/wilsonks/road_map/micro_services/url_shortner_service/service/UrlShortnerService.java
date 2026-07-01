package org.wilsonks.road_map.micro_services.url_shortner_service.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.wilsonks.road_map.micro_services.url_shortner_service.entity.Url;
import org.wilsonks.road_map.micro_services.url_shortner_service.repository.UrlRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UrlShortnerService {
    private final UrlRepository repository;

    public static final String BASE_URL = "my.bit.ly/";

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public String shortnerUrl(String originalUrl) {
        Optional<Url> existingUrl = repository.findByOriginalUrl(originalUrl);
        //1. Check existing URL in the database
        if (existingUrl.isPresent()) {
            log.info("Original URL {} already exists. Returning existing short URL.", originalUrl);
            return existingUrl.get().getShortUrl();
        }


        try {
            //2. Save the original URL to the database
            Url url = repository.save(new Url(null, originalUrl, null));

            //3. Take the generated ID and encode it to create a short URL
            String shortUrl = encodeShortUrl(url.getId());

            //4. Update the URL entity with the generated short URL and save it back to the database
            url.setShortUrl(shortUrl);
            repository.save(url);


            log.info("Original URL {} shortened to {}", originalUrl, shortUrl);
            return shortUrl;

        } catch (DataIntegrityViolationException e) {
            log.error("Data integrity violation while saving URL: {}", e.getMessage());

            return repository.findByOriginalUrl(originalUrl)
                    .map(Url::getShortUrl)
                    .orElseThrow(() -> new RuntimeException("Failed to shorten URL due to data integrity violation."));
        }


    }

    private String encodeShortUrl(Long id) {
        //Base 62 encoding to generate a short URL
        String base62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder shortUrl = new StringBuilder();

        if (id == 0) {
            return BASE_URL + "0";
        }

        while (id > 0) {
            int lastDigit = (int) (id % 62);
            shortUrl.append(base62.charAt(lastDigit));
            id /= 62;
        }


        return BASE_URL + shortUrl.reverse().toString();
    }
}
