package org.wilsonks.road_map.advanced.wallet_service.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.wilsonks.road_map.advanced.wallet_service.entities.TransactionType;
import org.wilsonks.road_map.advanced.wallet_service.entities.Wallet;
import org.wilsonks.road_map.advanced.wallet_service.entities.WalletTransaction;
import org.wilsonks.road_map.advanced.wallet_service.repository.WalletRepository;
import org.wilsonks.road_map.advanced.wallet_service.repository.WalletTransactionRepository;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class WalletService {
    private final WalletRepository repository;
    private final WalletTransactionRepository transactionRepository;

    @Transactional
    public Wallet bet(Long playerId, BigDecimal amount, String transactionId, String machineId, String gameId, String roundId){
        //1. Check Duplicate Transaction
        if(transactionRepository.findByTransactionId(transactionId).isPresent()) {
            throw new IllegalStateException("Duplicate Transaction");
        }
        //2. GetWalletById
        Wallet wallet = repository.findByPlayerId(playerId).orElseThrow();
        //3. Balance >= amount
        if(wallet.getBalance().compareTo(amount) < 0) {
            throw new IllegalStateException("Insufficient Balance");
        }
        //4. Record A Ledger Transaction
        BigDecimal before = wallet.getBalance();
        BigDecimal after = before.subtract(amount);
        wallet.setBalance(after);

        WalletTransaction tx = WalletTransaction.builder()
                .transactionId(transactionId)
                .type(TransactionType.BET)
                .wallet(wallet)
                .amount(amount)
                .balanceBefore(before)
                .balanceAfter(after)
                .machineId(machineId)
                .gameId(gameId)
                .roundId(roundId)
                .build();

        transactionRepository.save(tx);

        //5. Update Balance
        repository.save(wallet);

        return wallet;
    }

//    @Transactional
//    public Wallet credit(Long playerId, BigDecimal amount, String transactionId){}
//
//    @Transactional
//    public Wallet deposit(Long playerId, BigDecimal amount, String transactionId){}
//
//    @Transactional
//    public Wallet withdraw(Long playerId, BigDecimal amount, String transactionId){}

}
