package com.ooad.inventorymanagmentsystem.controllers;

import com.ooad.inventorymanagmentsystem.models.*;
import com.ooad.inventorymanagmentsystem.repositories.*;
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
public class UserPaymentController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @GetMapping("/payment")
    public String showCreatePage(Model model) {
        ReceiptDto receiptDto = new ReceiptDto();
        model.addAttribute("receiptDto", receiptDto);
        return "user/payment";
    }

    @PostMapping("/payment")
    public String placeOrder(@ModelAttribute("receiptDto") ReceiptDto receiptDto, BindingResult result, @AuthenticationPrincipal User userDetails, Model model) {
        if(receiptDto.getReceiptFile().isEmpty()) {
            result.addError(new FieldError("receiptDto", "receiptFile", "receipt is required"));
        }

        if(result.hasErrors()) {
            return "user/payment";
        }

        MultipartFile receipt = receiptDto.getReceiptFile();
        AppUser appUser = appUserRepository.findByEmail(userDetails.getUsername());
        Date paidAt = new Date();
        String storageFileName = paidAt.getTime() + "_" + appUser.getUserId() + receipt.getOriginalFilename();

        try {
            String uploadDir = "public/images/receipts";
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try (InputStream inputStream = receipt.getInputStream()) {
                Files.copy(inputStream, Paths.get(uploadDir, storageFileName), StandardCopyOption.REPLACE_EXISTING);
            }

        } catch (Exception ex) {
            System.out.println("Exception1: " + ex.getMessage());
        }

        //create Order
        Order order = new Order();
        order.setAppUser(appUser);
        //order.setHasComplaint(false);

        //calculateTotalAMount
        double totalAmount = 0.0;
        List<Cart> allCarts = cartRepository.findAllByUserId(appUser.getUserId());
        for (Cart cart : allCarts) {
            totalAmount = totalAmount + (cart.getProduct().getPrice()*cart.getQuantity());
        }

        order.setTotalAmount(totalAmount);
        orderRepository.save(order);

        //create Payment
        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setPaymentDate(order.getOrder_date());
        payment.setAmount(totalAmount);
        payment.setReceiptFileName(storageFileName);
        paymentRepository.save(payment);

        //creat order_details for each cart
        for (Cart cart : allCarts) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setProduct(cart.getProduct());
            orderDetail.setQuantity(cart.getQuantity());
            orderDetail.setPrice(cart.getProduct().getPrice());
            orderDetailRepository.save(orderDetail);
        }

        //remove cart items
        cartRepository.deleteAll(allCarts);

        return "redirect:/user/orders";

    }

}
