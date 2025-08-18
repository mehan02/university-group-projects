package com.ooad.inventorymanagmentsystem.models;

import jakarta.validation.constraints.NotEmpty;

public class SupplierDto {

    private int supplierId;

    @NotEmpty(message = "supplierName is required")
    private String supplierName;

    @NotEmpty(message = "supplierContact is required")
    private String supplierContact;

    @NotEmpty(message = "supplierAddress is required")
    private String supplierAddress;

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierContact() {
        return supplierContact;
    }

    public void setSupplierContact(String supplierContact) {
        this.supplierContact = supplierContact;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }
}
