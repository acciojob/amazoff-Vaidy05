package com.driver;

public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {

        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
        int hour = (deliveryTime.charAt(0)-48)*10 + (deliveryTime.charAt(1)-48);
        int minutes = (deliveryTime.charAt(3)-48)*10 + (deliveryTime.charAt(4)-48);

        this.id = id;
        this.deliveryTime = hour*60 + minutes;
    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}

    public void setId(String id) {
        this.id = id;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
}
