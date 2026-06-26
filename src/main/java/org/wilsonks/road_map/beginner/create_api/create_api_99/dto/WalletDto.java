package org.wilsonks.road_map.beginner.create_api.create_api_99.dto;

import java.math.BigDecimal;

//Modern clean DTO
public record WalletDto(String playerId, BigDecimal balance) {
}