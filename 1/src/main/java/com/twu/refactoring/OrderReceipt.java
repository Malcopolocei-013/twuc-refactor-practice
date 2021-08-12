package com.twu.refactoring;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 * 
 */
public class OrderReceipt {
	public static final String PRINTING_HEADERS = "======Printing Orders======";
	public static final String SALES_TAX = "Sales Tax";
	public static final String TOTAL_AMOUNT = "Total Amount";
	public static final double TAX_RATE = .10;
	private Order order;

    public OrderReceipt(Order order) {this.order = order;}

	public String printReceipt() {
		double totalSalesTax;
		double totalFee;
		StringBuilder output = new StringBuilder();

		totalFee = order.getLineItems().stream().mapToDouble(LineItem::getTotalAmount).sum();
		totalSalesTax = totalFee * TAX_RATE;

		totalFee += totalSalesTax;
		printer(totalSalesTax, totalFee, output);
		return output.toString();
	}

	private void printer(double totalSalesTax, double totalFee, StringBuilder output) {
		output.append(PRINTING_HEADERS + "\n").append(order.toString());
		order.toString();
		output.append(SALES_TAX).append('\t').append(totalSalesTax);
		output.append(TOTAL_AMOUNT).append('\t').append(totalFee);
	}
}