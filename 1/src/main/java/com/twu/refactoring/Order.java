package com.twu.refactoring;

import java.util.List;

public class Order {
    String name;
    String address;
    List<LineItem> lineItems;

    public Order(String nm, String addr, List<LineItem> li) {
        this.name = nm;
        this.address = addr;
        this.lineItems = li;
    }

    public String getCustomerName() {
        return name;
    }

    public String getCustomerAddress() {
        return address;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }
}
