package org.wilsonks.road_map.micro_services.wallet_service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class WalletServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(WalletServiceApp.class, args);
        log.info("Wallet Service is running");
    }
}
