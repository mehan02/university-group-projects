package com.ooad.inventorymanagmentsystem.controllers;

import com.ooad.inventorymanagmentsystem.models.CartDto;
import com.ooad.inventorymanagmentsystem.models.Product;
import com.ooad.inventorymanagmentsystem.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    ProductRepository productRepo;

    @GetMapping({"", "/", "/user", "/user/"})
    public String viewShop(Model model) {
        CartDto cartDto = new CartDto();
        List<Product> products = productRepo.getAllAvailableProducts();
        model.addAttribute("products", products);
        model.addAttribute("cartDto", cartDto);
        return "user/index";
    }
}
