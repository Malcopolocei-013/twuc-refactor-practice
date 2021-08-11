package com.twu.refactoring;

import java.util.List;

public class Order {
    String name;
    String address;

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    List<LineItem> lineItems;

    public Order(String name, String address, List<LineItem> lineItems) {
        this.name = name;
        this.address = address;
        this.lineItems = lineItems;
    }

    @Override
    public String toString(){
        return this.name + this.address + this.lineItems.toString();
    }
}
