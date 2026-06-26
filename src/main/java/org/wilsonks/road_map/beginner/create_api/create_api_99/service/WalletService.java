package org.wilsonks.road_map.beginner.create_api.create_api_99.service;

import org.wilsonks.road_map.beginner.create_api.create_api_99.dto.WalletDto;

import java.util.Optional;

public interface WalletService {
    public Optional<WalletDto> getWallet(String userId);
}
