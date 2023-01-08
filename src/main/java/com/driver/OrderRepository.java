package com.driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderRepository {
    HashMap<String , Order> orderHashMap= new HashMap<>(); // for Order Details
    HashMap<String , DeliveryPartner> deliveryPartnerHashMap= new HashMap<>(); // for Partner Details
    HashMap<String , String> orderDeliveryPartnerHashMap = new HashMap<>(); // for  order-partner 1v1
    HashMap<String , ArrayList<String>> partnerOrdersHashMap = new HashMap<>(); // for partner - orders 1vM
    private int unassignedOrder =0;
    OrderRepository(){
        orderHashMap = new HashMap<>();
        deliveryPartnerHashMap = new HashMap<>();
        orderDeliveryPartnerHashMap = new HashMap<>();
        partnerOrdersHashMap = new HashMap<>();
    }


    public void addOrder(Order order) {
        orderHashMap.put(order.getId(),order);
        System.out.println(order.getId() + " " + order.getDeliveryTime());
        unassignedOrder++;
    }

    public void addPartner(String partnerId) {
        DeliveryPartner newPartner = new DeliveryPartner(partnerId);
        deliveryPartnerHashMap.put(partnerId,newPartner);
    }

    public void addOrderPartnerPair(String orderId, String partnerId) {
        unassignedOrder--;
        DeliveryPartner dp = deliveryPartnerHashMap.get(partnerId);
        dp.setNumberOfOrders(dp.getNumberOfOrders()+1);
        orderDeliveryPartnerHashMap.put(orderId,partnerId);
        ArrayList<String> ls = new ArrayList<>();
        if(partnerOrdersHashMap.get(partnerId)!=null){
            ls = partnerOrdersHashMap.get(partnerId);
        }
            ls.add(orderId);
            partnerOrdersHashMap.put(partnerId,ls);

    }

    public Order getOrderById(String orderId) {
        if (orderHashMap.containsKey(orderId))
            return orderHashMap.get(orderId);
        else
            return null;
    }

    public DeliveryPartner getPartnerById(String partnerId) {
        return deliveryPartnerHashMap.get(partnerId);
    }

    public Integer getOrderCountByPartnerId(String partnerId) {
        if(deliveryPartnerHashMap.containsKey(partnerId)){
            DeliveryPartner dp =  deliveryPartnerHashMap.get(partnerId);
            return dp.getNumberOfOrders();
        }
        return null;
    }

    public List<String> getOrdersByPartnerId(String partnerId) {
        if(partnerOrdersHashMap.containsKey(partnerId))
            return partnerOrdersHashMap.get(partnerId);
        return null;

    }

    public List<String> getAllOrders() {
        ArrayList<String> allOrders = new ArrayList<>();
        for(String orderId:orderHashMap.keySet()){
            allOrders.add(orderId);
        }
        return allOrders;
    }
//Check it
    public Integer getCountOfUnassignedOrders() {
        return unassignedOrder;
    }

    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId) {
        int count = 0;
        return count;
    }

    public String getLastDeliveryTimeByPartnerId(String partnerId) {
        String time = "";
        return time;
    }

    public void deletePartnerById(String partnerId) {
        if (!partnerOrdersHashMap.containsKey(partnerId)){

        }else{
            ArrayList<String> orders  = partnerOrdersHashMap.get(partnerId);
            unassignedOrder += orders.size();
            for (String order:orders){
                orderDeliveryPartnerHashMap.remove(order);
            }
            deliveryPartnerHashMap.remove(partnerId);
            partnerOrdersHashMap.remove(partnerId);
        }

    }

    public void deleteOrderById(String orderId) {
        orderHashMap.remove(orderId);
        String partner = orderDeliveryPartnerHashMap.get(orderId);
        if(orderDeliveryPartnerHashMap.containsKey(partner)){
            orderDeliveryPartnerHashMap.remove(orderId);
            int idx =0;
            for(String order:partnerOrdersHashMap.get(partner)){
                if (order.equals(orderId)) {
                    break;
                }
                idx++;
            }
            ArrayList<String> ls  =  partnerOrdersHashMap.get(partner);
            ls.remove(idx);
            partnerOrdersHashMap.put(partner,ls);
        }
        unassignedOrder++;
    }
}
