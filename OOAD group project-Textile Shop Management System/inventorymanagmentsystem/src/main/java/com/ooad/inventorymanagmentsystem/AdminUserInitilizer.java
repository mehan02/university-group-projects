package com.ooad.inventorymanagmentsystem;

import com.ooad.inventorymanagmentsystem.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import com.ooad.inventorymanagmentsystem.models.AppUser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AdminUserInitilizer implements CommandLineRunner {

    @Autowired
    private AppUserRepository appUserRepository;


    @Override
    public void run(String... args) throws Exception {

        AppUser adminUser = appUserRepository.findByEmail("admin@ooad.com");

        if (adminUser == null) {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

            AppUser newAdmin = new AppUser();
            newAdmin.setFirstName("Admin");
            newAdmin.setLastName("User");
            newAdmin.setEmail("admin@ooad.com");
            newAdmin.setRole("ADMIN");
            newAdmin.setCreatedAt(new Date());
            newAdmin.setPassword(bCryptPasswordEncoder.encode("123456789"));  // set a default password

            appUserRepository.save(newAdmin);

            System.out.println("Admin user created successfully!");
        } else {
            System.out.println("Admin user already exists.");
        }
    }

}
