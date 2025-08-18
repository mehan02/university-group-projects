package com.ooad.inventorymanagmentsystem.repositories;

import com.ooad.inventorymanagmentsystem.models.Cart;
import com.ooad.inventorymanagmentsystem.models.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {

    @Query(value = "SELECT * FROM complaints WHERE user_id = :userId", nativeQuery = true)
    List<Complaint> findAllByUserId(@Param("userId")int userId);


}
