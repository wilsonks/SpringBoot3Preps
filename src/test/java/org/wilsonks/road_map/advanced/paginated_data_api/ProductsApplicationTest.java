package org.wilsonks.road_map.advanced.paginated_data_api;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.wilsonks.road_map.advanced.paginated_data_api.entities.Product;
import org.wilsonks.road_map.advanced.paginated_data_api.repository.ProductsRepository;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductsApplicationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProductsRepository repository;

    @BeforeEach
    void setup() {
        System.out.println("Setting up test data...");
        // Here you can set up any test data if needed
        repository.deleteAll();
        repository.save(new Product(null, "Product1", 10.0));
        repository.save(new Product(null, "Product2", 20.0));
        repository.save(new Product(null, "Product3", 30.0));
        repository.save(new Product(null, "Product4", 40.0));
        repository.save(new Product(null, "Product5", 50.0));
    }

    @Test
    void getAllProducts() {
        ResponseEntity<Map> response = restTemplate.getForEntity("/api/products", Map.class);
        assertEquals(200, response.getStatusCodeValue());

    }

}