package com.ooad.inventorymanagmentsystem.controllers;

import com.ooad.inventorymanagmentsystem.models.Product;
import com.ooad.inventorymanagmentsystem.repositories.OrderDetailRepository;
import com.ooad.inventorymanagmentsystem.repositories.OrderRepository;
import com.ooad.inventorymanagmentsystem.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @GetMapping({"", "/"})
    public String showDashBoard(Model model) {
        Double totalSalesToday = orderRepository.getTotalSalesToday();
        Double totalSalesThisWeek = orderRepository.getTotalSalesThisWeek();
        Double totalSalesThisMonth = orderRepository.getTotalSalesThisMonth();
        Double totalSalesPastSixMonths = orderRepository.getTotalSalesLastSixMonths();
        List<Product> lowStockProducts = productRepository.getLowStockProducts();

        if (totalSalesToday == null) {
            totalSalesToday = 0.0;
        }
        if (totalSalesThisWeek == null) {
            totalSalesThisWeek = 0.0;
        }
        if (totalSalesThisMonth == null) {
            totalSalesThisMonth = 0.0;
        }
        if (totalSalesPastSixMonths == null) {
            totalSalesPastSixMonths = 0.0;
        }


        model.addAttribute("totalSalesToday", totalSalesToday);
        model.addAttribute("totalSalesThisWeek", totalSalesThisWeek);
        model.addAttribute("totalSalesThisMonth", totalSalesThisMonth);
        model.addAttribute("totalSalesPastSixMonths", totalSalesPastSixMonths);
        model.addAttribute("lowStockProducts", lowStockProducts);
        return "admin/index";
    }
}
