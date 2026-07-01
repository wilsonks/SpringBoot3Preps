package org.wilsonks.road_map.micro_services.url_shortner_service.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "urls")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String originalUrl;

    private String shortUrl;
}
