package org.wilsonks.road_map.beginner.create_api.create_api_99.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.wilsonks.road_map.beginner.create_api.create_api_99.dto.WalletDto;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class WalletServiceImp2 implements WalletService {

    private final ConcurrentHashMap<String, BigDecimal> wallets = new ConcurrentHashMap<>();

    @PostConstruct
    public void Init() {
        wallets.put("1", BigDecimal.valueOf(1000));
        wallets.put("2", BigDecimal.valueOf(2000));
        wallets.put("3", BigDecimal.valueOf(3000));
    }

    @Override
    public Optional<WalletDto> getWallet(String playerId) {
        BigDecimal balance = wallets.get(playerId);

        return Optional.ofNullable(balance)
                .map(bal -> new WalletDto(playerId, bal));
    }
}
