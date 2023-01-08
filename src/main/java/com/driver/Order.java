package com.driver;

import java.util.Arrays;

public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {

        this.id = id;
        String[] temp = deliveryTime.split(":");
        this.deliveryTime = (Integer.parseInt(temp[0]) * 60) + Integer.parseInt(temp[1]);
        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}
}
