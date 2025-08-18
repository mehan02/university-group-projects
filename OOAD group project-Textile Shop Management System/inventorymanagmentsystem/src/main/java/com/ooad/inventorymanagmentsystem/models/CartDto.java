package com.ooad.inventorymanagmentsystem.models;

public class CartDto {

    private int userId;  // Instead of the AppUser object, you can store the userId
    private int productId; // Instead of the Product object, store the productId
    private int quantity;

    public CartDto() {
    }

    public CartDto(int userId, int productId, int quantity) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
