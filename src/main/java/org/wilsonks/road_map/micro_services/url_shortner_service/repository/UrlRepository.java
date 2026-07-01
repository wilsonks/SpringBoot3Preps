package org.wilsonks.road_map.micro_services.url_shortner_service.repository;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;
import org.wilsonks.road_map.micro_services.url_shortner_service.entity.Url;

import java.util.Optional;


@Repository
public interface UrlRepository extends JpaRepository<Url,Long> {

    Optional<Url> findByOriginalUrl(String originalUrl);
}
