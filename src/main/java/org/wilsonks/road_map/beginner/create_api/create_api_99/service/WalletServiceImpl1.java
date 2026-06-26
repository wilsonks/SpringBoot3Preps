package org.wilsonks.road_map.beginner.create_api.create_api_99.service;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.wilsonks.road_map.beginner.create_api.create_api_99.dto.WalletDto;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class WalletServiceImpl1 implements WalletService {

    private final ConcurrentHashMap<String, BigDecimal> wallets = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        wallets.put("1", BigDecimal.valueOf(3000));
        wallets.put("2", BigDecimal.valueOf(5000));
    }

    @Override
    public Optional<WalletDto> getWallet(String playerId) {
        log.info("Fetching wallet for for playerId= {}", playerId);
        BigDecimal balance = wallets.get(playerId);

        return Optional.ofNullable(balance)
                .map(bal -> new WalletDto(playerId, bal));
    }
}
