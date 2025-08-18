package com.ooad.inventorymanagmentsystem.controllers;

import com.ooad.inventorymanagmentsystem.models.Complaint;
import com.ooad.inventorymanagmentsystem.repositories.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminComplaintController {

    @Autowired
    ComplaintRepository complaintRepository;

    @GetMapping("/complaints")
    public String userComplaints(Model model) {
        model.addAttribute("complaints", complaintRepository.findAll());
        return "admin/complaints";
    }

    @GetMapping("/delete-complaint")
    public String deleteCart(@RequestParam("complaintId") int id) {

        Complaint complaint = complaintRepository.findById(id).get();

        try {
            complaintRepository.deleteById(id);
        }
        catch (Exception ex) {
            System.out.println("Exception_delete_complaint: " + ex.getMessage());
        }

        return "redirect:/admin/complaints";
    }

}
