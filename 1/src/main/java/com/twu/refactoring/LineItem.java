package com.twu.refactoring;

public class LineItem {
	private String description;
	private double price;
	private int quantity;

	public LineItem(String description, double price, int quantity) {
		this.description = description;
		this.price = price;
		this.quantity = quantity;
	}

    double getTotalAmount() {
        return price * quantity;
    }

    @Override
	public String toString(){
		return this.description + "\t" + this.price + "\t" +this.quantity + "\t" + this.getTotalAmount() + "\n";
	}
}