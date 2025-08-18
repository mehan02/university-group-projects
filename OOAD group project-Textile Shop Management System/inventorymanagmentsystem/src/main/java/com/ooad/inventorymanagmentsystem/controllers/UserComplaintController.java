package com.ooad.inventorymanagmentsystem.controllers;

import com.ooad.inventorymanagmentsystem.models.*;
import com.ooad.inventorymanagmentsystem.repositories.AppUserRepository;
import com.ooad.inventorymanagmentsystem.repositories.ComplaintRepository;
import com.ooad.inventorymanagmentsystem.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserComplaintController {

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    ComplaintRepository complaintRepository;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/complaints")
    public String viewComplaint(Model model, @AuthenticationPrincipal User userDetails) {
        AppUser appUser = appUserRepository.findByEmail(userDetails.getUsername());
        List<Complaint> userComplaints = complaintRepository.findAllByUserId(appUser.getUserId());
        model.addAttribute("userComplaints", userComplaints);
        return "user/complaints";
    }

    @GetMapping("/create-complaint/{id}")
    public String showCreatePage(@PathVariable("id") Integer orderId, Model model) {
        model.addAttribute("orderId", orderId);
        return "user/create-complaint";
    }

    @PostMapping("/create-complaint")
    public String fileComplaint(@RequestParam("orderId") Integer orderId,@RequestParam("complaintText") String complaintText,  @AuthenticationPrincipal User userDetails) {


        //create Complaint
        Complaint complaint = new Complaint();

        AppUser appUser = appUserRepository.findByEmail(userDetails.getUsername());
        Order order = orderRepository.findById(orderId).get();

        complaint.setAppUser(appUser);
        complaint.setOrder(order);
        complaint.setComplaintText(complaintText);
        complaintRepository.save(complaint);

        return "redirect:/user/complaints";

    }

    @GetMapping("/delete-complaint")
    public String deleteCart(@RequestParam int id) {

        Complaint complaint = complaintRepository.findById(id).get();

        try {
            complaintRepository.deleteById(id);
        }
        catch (Exception ex) {
            System.out.println("Exception_delete_complaint: " + ex.getMessage());
        }

        return "redirect:/user/complaints";
    }

}
