package edu.gatech.seclass.tccart.business;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bulentcoskun on 4/6/2016.
 */
public class Order {

    List<Item> orderItems = new ArrayList<Item>();
    public void AddItem(Item i){
        orderItems.add(i);
    }
    public void RemoveItem(Item i){
        orderItems.remove(i);
    }

    public List<Item> getOrderItems()
    {
        return orderItems;
    }
}
