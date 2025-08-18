package com.ooad.inventorymanagmentsystem.repositories;

import com.ooad.inventorymanagmentsystem.models.Cart;
import com.ooad.inventorymanagmentsystem.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query(value = "SELECT * FROM orders WHERE user_id = :userId", nativeQuery = true)
    List<Order> findAllByUserId(@Param("userId")int userId);


    // Query to get total sales today
    @Query(value = "SELECT SUM(total_amount) FROM orders WHERE DATE(order_date) = CURDATE()", nativeQuery = true)
    Double getTotalSalesToday();

    // Query to get total sales this week
    @Query(value = "SELECT SUM(total_amount) FROM orders WHERE YEARWEEK(order_date, 1) = YEARWEEK(CURDATE(), 1)", nativeQuery = true)
    Double getTotalSalesThisWeek();

    // Query to get total sales this month
    @Query(value = "SELECT SUM(total_amount) FROM orders WHERE YEAR(order_date) = YEAR(CURDATE()) AND MONTH(order_date) = MONTH(CURDATE())", nativeQuery = true)
    Double getTotalSalesThisMonth();

    // Query to get total sales for last 6 months
    @Query(value = "SELECT SUM(total_amount) FROM orders WHERE order_date >= CURDATE() - INTERVAL 6 MONTH", nativeQuery = true)
    Double getTotalSalesLastSixMonths();


}
