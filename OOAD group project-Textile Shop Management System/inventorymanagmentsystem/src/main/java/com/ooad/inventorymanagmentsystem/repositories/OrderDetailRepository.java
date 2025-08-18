package com.ooad.inventorymanagmentsystem.repositories;

import com.ooad.inventorymanagmentsystem.models.Order;
import com.ooad.inventorymanagmentsystem.models.OrderDetail;
import com.ooad.inventorymanagmentsystem.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    @Query(value = "SELECT * FROM order_detail WHERE order_id = :orderId", nativeQuery = true)
    List<OrderDetail> findAllByOrderId(@Param("orderId")int orderId);



}
