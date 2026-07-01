package org.wilsonks.road_map.micro_services.wallet_service.entities;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Table(name = "t_wallet_transactions")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WalletTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String transactionId; //Idempotency Key

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private BigDecimal amount;

    private BigDecimal balanceBefore;

    private BigDecimal balanceAfter;

    private String gameId;

    private String roundId;

    private String machineId;


}
