package com.ooad.inventorymanagmentsystem.controllers;

import com.ooad.inventorymanagmentsystem.models.AppUser;
import com.ooad.inventorymanagmentsystem.models.RegisterDto;
import com.ooad.inventorymanagmentsystem.repositories.AppUserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@Controller
public class UserAccountController {

    @Autowired
    private AppUserRepository appUserRepository;

    @GetMapping({"/register", "/user/register"})
    public String register(Model model) {
        RegisterDto registerDto = new RegisterDto();
        model.addAttribute(registerDto);
        model.addAttribute("success", false);
        return "user/register";
    }

    @PostMapping({"/register", "/user/register"})
    public String register(@Valid @ModelAttribute RegisterDto registerDto, BindingResult bindingResult, Model model) {

        //empty fields errors
        if(registerDto.getFirstName() == null) {
            bindingResult.addError(
                    new FieldError("registerDto", "firstName", "First name is required")
            );
        }
        if(registerDto.getLastName() == null) {
            bindingResult.addError(
                    new FieldError("registerDto", "lastName", "Last name is required")
            );
        }
        if(registerDto.getEmail() == null) {
            bindingResult.addError(
                    new FieldError("registerDto", "email", "Email is required")
            );
        }
        if(registerDto.getPhone() == null) {
            bindingResult.addError(
                    new FieldError("registerDto", "phone", "Phone is required")
            );
        }
        if(registerDto.getAddress() == null) {
            bindingResult.addError(
                    new FieldError("registerDto", "address", "Address is required")
            );
        }
        if(registerDto.getPassword() == null) {
            bindingResult.addError(
                    new FieldError("registerDto", "password", "Password is required")
            );
        }


        //if passwords didnt match
        if(!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
            bindingResult.addError(
                    new FieldError("registerDto", "confirmPassword", "Passwords do not match")
            );
        }



        AppUser appUser = appUserRepository.findByEmail(registerDto.getEmail());
        if(appUser != null) {
            bindingResult.addError(
                    new FieldError("registerDto", "email", "Email is already in use")
            );
        }

        if(bindingResult.hasErrors()) {
            return "user/register";
        }

        try {
            var bCryptEncoder = new BCryptPasswordEncoder();

            AppUser newUser = new AppUser();
            newUser.setFirstName(registerDto.getFirstName());
            newUser.setLastName(registerDto.getLastName());
            newUser.setEmail(registerDto.getEmail());
            newUser.setPhone(registerDto.getPhone());
            newUser.setAddress(registerDto.getAddress());
            newUser.setRole("USER");
            newUser.setCreatedAt(new Date());
            newUser.setPassword(bCryptEncoder.encode(registerDto.getPassword()));

            appUserRepository.save(newUser);

            //clear the register form
            model.addAttribute("registerDto", new RegisterDto());
            model.addAttribute("success", true);
        }
        catch(Exception ex) {
            bindingResult.addError(
                    new FieldError("registerDto", "firstName", ex.getMessage())
            );
        }

        return "user/register";
    }
}