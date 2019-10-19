package com.onlinekaufen.springframework.dto;

import java.io.Serializable;

public class CartSessionDTO implements Serializable {

    private int itemId;
    private int itemQty;

    public CartSessionDTO() {
    }

    public CartSessionDTO(int itemId, Integer itemQty) {
        this.itemId = itemId;
        this.itemQty = itemQty;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public Integer getItemQty() {
        return itemQty;
    }

    public void setItemQty(Integer itemQty) {
        this.itemQty = itemQty;
    }
}
