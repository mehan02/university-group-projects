package com.ooad.inventorymanagmentsystem.controllers;

import com.ooad.inventorymanagmentsystem.models.AppUser;
import com.ooad.inventorymanagmentsystem.models.Complaint;
import com.ooad.inventorymanagmentsystem.models.Order;
import com.ooad.inventorymanagmentsystem.models.OrderDetail;
import com.ooad.inventorymanagmentsystem.repositories.AppUserRepository;
import com.ooad.inventorymanagmentsystem.repositories.ComplaintRepository;
import com.ooad.inventorymanagmentsystem.repositories.OrderDetailRepository;
import com.ooad.inventorymanagmentsystem.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserOrderController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    ComplaintRepository complaintRepository;

    @GetMapping("/orders")
    public String orders(@AuthenticationPrincipal User userDetails, Model model) {
        AppUser appUser = appUserRepository.findByEmail(userDetails.getUsername());
        List<Order> userOrders = orderRepository.findAllByUserId(appUser.getUserId());
//        List<Complaint> userComplaints = complaintRepository.findAllByUserId(appUser.getUserId());
        List<OrderDetail> userOrderDetails = new ArrayList<>();

        for (Order order : userOrders) {
            List<OrderDetail> orderDetails = orderDetailRepository.findAllByOrderId(order.getOrderId());
            userOrderDetails.addAll(orderDetails);
        }
        //List<OrderDetail> userOrderDetails = orderDetailRepository.findAllByOrderId();
        model.addAttribute("userOrders", userOrders);
//        model.addAttribute("userComplaints", userComplaints);
        model.addAttribute("userOrderDetails", userOrderDetails);
        return "user/orders";
    }
}
