package org.wilsonks.road_map.advanced.paginated_data_api.controller;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wilsonks.road_map.advanced.paginated_data_api.entities.Product;
import org.wilsonks.road_map.advanced.paginated_data_api.repository.ProductsRepository;

import java.util.Map;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
@Slf4j
public class ProductsController {

    private  final ProductsRepository repository;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getProducts(
            @RequestParam String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        // Here you would typically call a service to fetch the products from the database
//        Page<Product> productPage = repository.findAll(pageable);
        Page<Product> productPage = repository.findByNameContaining(search, pageable);

        // For demonstration purposes, we'll return a mock response
        Map<String, Object> response = Map.of(
                "products", new String[]{"Product1", "Product2"},
                "currentPage", productPage.getNumber(),
                "totalItems", productPage.getTotalElements(),
                "totalPages", productPage.getTotalPages()
        );

        return ResponseEntity.ok(response);


    }

}
