package org.wilsonks.road_map.beginner.create_api.create_api_98.service;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.Predicate;

@Service
@Slf4j
public class UserService {



    Supplier<String> uuidSupplier = () -> UUID.randomUUID().toString();
    Supplier<Double> doubleSupplier = () -> (Math.random() * 10) + 1;
    Supplier<Integer> integerSupplier = () -> new Random().nextInt(10);

    Consumer<String> print = (s) -> log.info( "Consumer "+ s);
    Predicate<String> filter = (s) -> "ROLE_".contains(s);

    Function<String, Integer> leng = (s) -> s.length();

    @PostConstruct
    public void init() {
        log.info("A uid string supplied by uid supplier {}", uuidSupplier.get());
        log.info("A double supplied by double supplier {}", doubleSupplier.get());
        log.info("An integer supplied by Integer supplier {}", integerSupplier.get());
        print.accept("Sample Print");
    }

}
