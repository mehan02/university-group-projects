package com.ooad.inventorymanagmentsystem.controllers;

import com.ooad.inventorymanagmentsystem.models.*;
import com.ooad.inventorymanagmentsystem.repositories.AppUserRepository;
import com.ooad.inventorymanagmentsystem.repositories.CartRepository;
import com.ooad.inventorymanagmentsystem.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserCartController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private ProductRepository productRepository;


    @GetMapping("/cart")
    public String viewCart(Model model, @AuthenticationPrincipal User userDetails) {
        AppUser appUser = appUserRepository.findByEmail(userDetails.getUsername());
        List<Cart> cartList = cartRepository.findAllByUserId(appUser.getUserId());
        model.addAttribute("cartList", cartList);
        return "user/cart";
    }


    @PostMapping("/create-cart")
    public String createCart(@ModelAttribute CartDto cartDto, BindingResult bindingResult,  @AuthenticationPrincipal User userDetails) {
        Product product = productRepository.findById(cartDto.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + cartDto.getProductId() + "quantity: " + cartDto.getQuantity()));
        if(cartDto.getQuantity() > product.getStockQuantity()) {
            bindingResult.addError(
                    new FieldError("cartDto", "quantity", "Not Enough Stock")
            );
        }
        if(bindingResult.hasErrors()) {
            return "user/index";
        }
        Cart cart = new Cart();
        AppUser appUser = appUserRepository.findByEmail(userDetails.getUsername());
        cart.setAppUser(appUser);
        cart.setProduct(product);
        cart.setQuantity(cartDto.getQuantity());
        cartRepository.save(cart);
        //deduce stock
        product.setStockQuantity(product.getStockQuantity() - cartDto.getQuantity());
        productRepository.save(product);
        return "redirect:/user/";
    }


    @GetMapping("/delete-cart")
    public String deleteCart(@RequestParam int id) {

        Cart cart = cartRepository.findById(id).get();
        Product product = cart.getProduct();
        product.setStockQuantity(product.getStockQuantity() + cart.getQuantity());

        try {
            cartRepository.deleteById(id);
        }
        catch (Exception ex) {
            System.out.println("Exception_delete_cart: " + ex.getMessage());
        }

        return "redirect:/user/cart";
    }
}
