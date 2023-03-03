package com.ssf.assessment.model;

public class Checkout {
    
    private String id="";
    private String name="";
    private String address="";
    private float total=0f;
    private ShippingAddress shippingAddress;
    
    public Checkout(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
        this.name=this.shippingAddress.getName();
        this.address=this.shippingAddress.getAddress();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    

    
}
