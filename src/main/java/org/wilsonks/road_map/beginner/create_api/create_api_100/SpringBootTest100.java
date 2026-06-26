package org.wilsonks.road_map.beginner.create_api.create_api_100;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class SpringBootTest100 {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootTest100.class, args);
    }
}


@Component
class MyCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Command Line Runner");
    }
}
