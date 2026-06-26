package org.wilsonks.road_map.beginner.create_api.create_api_99.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wilsonks.road_map.beginner.create_api.create_api_99.dto.ApiError;
import org.wilsonks.road_map.beginner.create_api.create_api_99.dto.WalletDto;
import org.wilsonks.road_map.beginner.create_api.create_api_99.service.WalletService;

import java.util.Optional;

@RestController
@RequestMapping("/api/wallets")
public class WalletApiController {

    private final WalletService walletService;

    public WalletApiController(@Qualifier("walletServiceImp2") WalletService walletService) {
        this.walletService = walletService;
    }


    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getWallet(@PathVariable String userId, HttpServletRequest request) {
        if(userId.isBlank()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Id cannot be null");
        }

        Optional<WalletDto> balanceDto = walletService.getWallet(userId);

        if(balanceDto.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ApiError(404, "NOT_FOUND", "Wallet Not found", request.getRequestURI()));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(balanceDto.get());
    }
}
