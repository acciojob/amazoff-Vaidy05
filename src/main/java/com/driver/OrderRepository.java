package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class OrderRepository {

   private HashMap<String,Order> orderHashMap;

   private HashMap<String,DeliveryPartner> deliveryPartnerHashMap;

   private HashMap<String,List<String>> deliveryPartnerOrderMap;

   private HashMap<String,String> OrderPartnerMap;

   public OrderRepository(){
         this.orderHashMap = new HashMap<>();
         this.deliveryPartnerHashMap = new HashMap<>();
         this.deliveryPartnerOrderMap = new HashMap<>();
         this.OrderPartnerMap = new HashMap<>();
   }

   public void addOrder(Order order){
      orderHashMap.put(order.getId(),order);
   }

   public void addPartner(DeliveryPartner deliveryPartner){
       deliveryPartnerHashMap.put(deliveryPartner.getId(), deliveryPartner);
   }

   public void addPartnerOrderPair(String orderId,String deliveryPartnerId){
       List<String> orderList = new ArrayList<>();

       if(deliveryPartnerOrderMap.containsKey(orderId))
           orderList = deliveryPartnerOrderMap.get(orderId);

       orderList.add(orderId);

       deliveryPartnerOrderMap.put(deliveryPartnerId,orderList);

       deliveryPartnerHashMap.get(deliveryPartnerId).setNumberOfOrders(orderList.size());

       OrderPartnerMap.put(orderId,deliveryPartnerId);

   }

   public Order getOrder(String orderId){
       return orderHashMap.get(orderId);
   }

   public DeliveryPartner getPartner(String deliveryPartnerId){
       return deliveryPartnerHashMap.get(deliveryPartnerId);
   }

   public int getOrderCount(String parnterId){

       List<String> orderList = new ArrayList<>();

       orderList = deliveryPartnerOrderMap.get(parnterId);

       return orderList.size();
   }

   public List<String> getOrderByPartner(String partnerId){
       return deliveryPartnerOrderMap.get(partnerId);
   }

   public List<String> getAllOrder(){
       List<String> orderList = new ArrayList<>();

       for(String order : orderHashMap.keySet()){
           orderList.add(order);
       }

       return orderList;
   }

   public int getOrderUnassignedCount(){

       int count = 0;

       for(String order : orderHashMap.keySet()){
           if(!OrderPartnerMap.containsKey(order))
               count++;
       }

       return count;
   }

   public int getOrderCountLeft(String time, String partnerId){

       int hour = (time.charAt(0)-48)*10 + (time.charAt(1)-48);
       int minutes = (time.charAt(3)-48)*10 + (time.charAt(4)-48);

       int deliveryTime = hour*60 + minutes;

       List<String> orderList = new ArrayList<>();

       orderList = deliveryPartnerOrderMap.get(partnerId);

       int count = 0;

       for(String orderId : orderList){
           if(orderHashMap.get(orderId).getDeliveryTime()>deliveryTime)
               count++;
       }

       return count;
   }

   public String getLastDeliveryTime(String partnerId){

       List<String> orderList = new ArrayList<>();

       orderList = deliveryPartnerOrderMap.get(partnerId);

       int lastDelivery = 0;

       for(String orderId : orderList){
           lastDelivery = Math.max(lastDelivery,orderHashMap.get(orderId).getDeliveryTime());
       }

       int hour = lastDelivery/60;
       int minutes = lastDelivery%60;

       String time = "";
       time = time+hour+":"+minutes;

       return time;
   }

   public void deletePartner(String partnerId){

       deliveryPartnerOrderMap.remove(partnerId);

       for(String orderId : OrderPartnerMap.keySet()){
           if(OrderPartnerMap.get(orderId).equals(partnerId))
               OrderPartnerMap.remove(orderId);
       }

       deliveryPartnerHashMap.remove(partnerId);
   }

   public void deleteOrder(String orderId){

       String partnerId = OrderPartnerMap.get(orderId);

       List<String> orderList = new ArrayList<>();

       orderList = deliveryPartnerOrderMap.get(partnerId);

       for(String order : orderList){
           if(order.equals(orderId))
               orderList.remove(order);
       }

       deliveryPartnerOrderMap.put(partnerId,orderList);

       OrderPartnerMap.remove(orderId);

       orderHashMap.remove(orderId);
   }
}