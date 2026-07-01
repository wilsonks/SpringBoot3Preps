package org.wilsonks.road_map.advanced.wallet_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wilsonks.road_map.advanced.wallet_service.entities.Wallet;

import java.util.Optional;

@Repository
public interface WalletRepository
        extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findByPlayerId(Long playerId);
}
