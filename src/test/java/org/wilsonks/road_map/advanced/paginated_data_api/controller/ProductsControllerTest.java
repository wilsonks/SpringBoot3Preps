package org.wilsonks.road_map.advanced.paginated_data_api.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.wilsonks.road_map.advanced.paginated_data_api.repository.ProductsRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment =  SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductsControllerTest {


    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void getProducts() {
        String url = "http://localhost:" + port + "/api/products?page=0&size=5";
        var response = testRestTemplate.getForEntity(url, String.class);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());

    }
}