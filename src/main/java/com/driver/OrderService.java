package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

   @Autowired
    OrderRepository orderRepository = new OrderRepository();

    public void addOrder(Order order){
        orderRepository.addOrder(order);
    }

    public void addPartner(DeliveryPartner deliveryPartner){
        orderRepository.addPartner(deliveryPartner);
    }

    public void addPartnerOrderPair(String orderId,String deliveryPartnerId){
        orderRepository.addPartnerOrderPair(orderId,deliveryPartnerId);
    }

    public Order getOrder(String orderId){
        return orderRepository.getOrder(orderId);
    }

    public DeliveryPartner getPartner(String deliveryPartnerId){
        return orderRepository.getPartner(deliveryPartnerId);
    }

    public int getOrderCount(String parnterId){
        return orderRepository.getOrderCount(parnterId);
    }

    public List<String> getOrderByPartner(String partnerId){
        return orderRepository.getOrderByPartner(partnerId);
    }

    public List<String> getAllOrder(){
       return orderRepository.getAllOrder();
    }

    public int getOrderUnassignedCount(){
        return orderRepository.getOrderUnassignedCount();
    }

    public int getOrderCountLeft(String time, String partnerId){

        return orderRepository.getOrderCountLeft(time,partnerId);
    }

    public String getLastDeliveryTime(String partnerId){

        return getLastDeliveryTime(partnerId);
    }

    public void deletePartner(String partnerId){

        orderRepository.deletePartner(partnerId);
    }

    public void deleteOrder(String orderId){

        orderRepository.deleteOrder(orderId);
    }
}
