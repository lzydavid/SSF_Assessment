package com.ssf.assessment.model;

import jakarta.validation.constraints.Min;

public class Item {
    
    private String item;
    
    @Min(value = 1,message = "You must add at least 1 item")
    private int quantity;
    
    public Item() {
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ShoppingCart [item=" + item + ", quantity=" + quantity + "]";
    }
}
