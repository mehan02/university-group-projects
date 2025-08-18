package com.ooad.inventorymanagmentsystem.models;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

public class PaymentDto {

    private OrderDto orderDto;
    private LocalDateTime paymentDate;
    private double amount;
    private MultipartFile receiptFile;

    public OrderDto getOrderDto() {
        return orderDto;
    }

    public void setOrderDto(OrderDto orderDto) {
        this.orderDto = orderDto;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public MultipartFile getReceiptFile() {
        return receiptFile;
    }

    public void setReceiptFile(MultipartFile receiptFile) {
        this.receiptFile = receiptFile;
    }
}
