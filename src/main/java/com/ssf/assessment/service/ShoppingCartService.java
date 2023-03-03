package com.ssf.assessment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssf.assessment.model.Item;

@Service
public class ShoppingCartService {
    
    private List<Item> cart = new ArrayList<>();
    private Boolean empty = false;
    private static final String[] listOfItem = {"apple","orange","bread","cheese","chicken","mineral_water","instant_noodles"};

    public ShoppingCartService() {

    }

    public void addItem(Item item) {
        this.cart.add(item);
    }

    public List<Item> getCart() {
        return cart;
    }

    public Boolean getEmpty() {
        return empty;
    }

    public void setEmpty(Boolean empty) {
        this.empty = empty;
    }

    public Boolean inOption(String item) {
        for (int i = 0; i < listOfItem.length; i++) {
            if(listOfItem[i]==item){
                return true;
            }
        }
        return false;
    }
}
