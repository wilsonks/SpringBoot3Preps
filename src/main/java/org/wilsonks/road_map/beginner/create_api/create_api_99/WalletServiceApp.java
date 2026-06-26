package org.wilsonks.road_map.beginner.create_api.create_api_99;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@Slf4j
public class WalletServiceApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(WalletServiceApp.class, args);

        log.info("Wallet Service App is Up with {} Beans", context.getBeanDefinitionCount());

    }
}
