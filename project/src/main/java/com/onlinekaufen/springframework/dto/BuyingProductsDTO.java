package com.onlinekaufen.springframework.dto;

public class BuyingProductsDTO extends ProductsDTO {
    private int qty;

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
