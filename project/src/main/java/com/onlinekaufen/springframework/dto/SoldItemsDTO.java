package com.onlinekaufen.springframework.dto;

import java.sql.Timestamp;

public class SoldItemsDTO extends PurchaseItemsDTO {

    private Timestamp purchaseDate;
    private String productName;
    private float price;

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Timestamp getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Timestamp purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}
