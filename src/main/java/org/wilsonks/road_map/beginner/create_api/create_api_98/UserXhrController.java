package org.wilsonks.road_map.beginner.create_api.create_api_98;

import jakarta.annotation.PreDestroy;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wilsonks.road_map.beginner.create_api.create_api_98.model.UserDto;

import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@RestController
@RequestMapping("/api/users")
public class UserXhrController {

    ExecutorService ioPool = Executors.newFixedThreadPool(2);

    @GetMapping(value = "/{id}")
    public CompletableFuture<ResponseEntity<UserDto>> getUser(@PathVariable String id) {
        Supplier<UserDto> supplier = () -> {
            //Simulate Database
            try {
                // 1-second delay
                Thread.sleep(1000);

                //One specific id 99 trigegrs exception
                if("999".equals(id)) {
                    throw new RuntimeException("User Not found in DB");
                }

                return new UserDto("1", "Wilson", 44);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        return CompletableFuture.supplyAsync(supplier, ioPool)
                .thenApply(userDto -> {
                    return ResponseEntity.status(HttpStatus.OK).body(userDto);
                })
                .orTimeout(2, TimeUnit.SECONDS)
                .exceptionally(ex -> {

                   UserDto fallbackUserDto = new UserDto(id, "Unknown", 0);

                   return ResponseEntity
                           .status(HttpStatus.NOT_FOUND)
                           .body(fallbackUserDto);
                });
    }

    @PreDestroy
    public void cleanup() {
        ioPool.shutdown();
    }
}
