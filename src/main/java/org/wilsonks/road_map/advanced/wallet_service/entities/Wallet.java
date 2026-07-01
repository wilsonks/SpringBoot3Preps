package org.wilsonks.road_map.advanced.wallet_service.entities;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;

@Table(name = "t_wallets")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "playerId")
    private Long playerId;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal balance;

    @Version
    Long version;

}
