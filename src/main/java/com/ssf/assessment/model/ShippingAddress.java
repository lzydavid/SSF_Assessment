package com.ssf.assessment.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ShippingAddress {
    
    @NotNull(message = "Please Enter Your Name")
    @NotEmpty(message = "Please Enter Your Name")
    private String name;
    
    @NotNull(message = "Please Enter Your Address for delivery")
    @NotEmpty(message = "Please Enter Your Address for delivery")
    private String address;
    
    public ShippingAddress() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
