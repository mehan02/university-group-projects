package com.ooad.inventorymanagmentsystem.repositories;

import com.ooad.inventorymanagmentsystem.models.OrderDetail;
import com.ooad.inventorymanagmentsystem.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "SELECT * FROM products WHERE stock_quantity > 0", nativeQuery = true)
    List<Product> getAllAvailableProducts();

    @Query(value = "SELECT * FROM products WHERE stock_quantity < 15 LIMIT 7", nativeQuery = true)
    List<Product> getLowStockProducts();
}
