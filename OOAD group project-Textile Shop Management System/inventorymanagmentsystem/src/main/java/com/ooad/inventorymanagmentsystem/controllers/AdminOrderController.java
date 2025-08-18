package com.ooad.inventorymanagmentsystem.controllers;

import com.ooad.inventorymanagmentsystem.models.AppUser;
import com.ooad.inventorymanagmentsystem.models.Order;
import com.ooad.inventorymanagmentsystem.models.OrderDetail;
import com.ooad.inventorymanagmentsystem.models.Payment;
import com.ooad.inventorymanagmentsystem.repositories.AppUserRepository;
import com.ooad.inventorymanagmentsystem.repositories.OrderDetailRepository;
import com.ooad.inventorymanagmentsystem.repositories.OrderRepository;
import com.ooad.inventorymanagmentsystem.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminOrderController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    PaymentRepository paymentRepository;

    @GetMapping("/orders")
    public String orders(Model model) {
        Sort sortByOrderId = Sort.by(Sort.Order.asc("orderId"));
        List<Order> orders = orderRepository.findAll(sortByOrderId);
        List<OrderDetail> orderDetails = orderDetailRepository.findAll();
        List<Payment> payments = paymentRepository.findAll();
        model.addAttribute("orders", orders);
        model.addAttribute("orderDetails", orderDetails);
        model.addAttribute("payments", payments);
        return "admin/orders";
    }

    @GetMapping("/all-oders")
    public String allOrders(Model model) {
        Sort sortByOrderId = Sort.by(Sort.Order.asc("orderId"));
        List<Order> orders = orderRepository.findAll(sortByOrderId);
        List<OrderDetail> orderDetails = orderDetailRepository.findAll();
        List<Payment> payments = paymentRepository.findAll();
        model.addAttribute("orders", orders);
        model.addAttribute("orderDetails", orderDetails);
        model.addAttribute("payments", payments);
        return "admin/all-orders";
    }

    @GetMapping("/confirm-order")
    public String confirmOrder(@RequestParam("orderId") int orderId) {
        Order order = orderRepository.findById(orderId).get();

        order.setStatus(Order.Status.CONFIRMED);

        orderRepository.save(order);

        return "redirect:/admin/orders";
    }
}
