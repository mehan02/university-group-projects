package com.ooad.inventorymanagmentsystem.models;

public class PopularItemDto {

    private String productName;
    private double totalSales;

    public PopularItemDto(String productName, double totalSales) {
        this.productName = productName;
        this.totalSales = totalSales;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(double totalSales) {
        this.totalSales = totalSales;
    }
}
