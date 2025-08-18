package com.ooad.inventorymanagmentsystem.repositories;

import com.ooad.inventorymanagmentsystem.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    AppUser findByEmail(String email);

}
