package org.wilsonks.road_map.advanced.wallet_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wilsonks.road_map.advanced.wallet_service.entities.WalletTransaction;

import java.util.Optional;

@Repository
public interface WalletTransactionRepository
        extends JpaRepository<WalletTransaction, Long> {
    Optional<WalletTransaction> findByTransactionId(String transactionId);
}
