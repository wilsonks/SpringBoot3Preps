package org.wilsonks.road_map.advanced.paginated_data_api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wilsonks.road_map.advanced.paginated_data_api.entities.Product;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {
    //By default, JpaRepository provides methods for pagination and sorting, so you don't need to add any additional methods here unless you want custom queries.
    Page<Product> findByNameContaining(String name, Pageable pageable);
}
