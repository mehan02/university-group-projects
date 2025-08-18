package com.ooad.inventorymanagmentsystem.models;

import org.springframework.web.multipart.MultipartFile;

public class ReceiptDto {

    private MultipartFile receiptFile;


    public MultipartFile getReceiptFile() {
        return receiptFile;
    }

    public void setReceiptFile(MultipartFile receiptFile) {
        this.receiptFile = receiptFile;
    }

}
