package com.twu.refactoring;

import java.util.ArrayList;
import java.util.Iterator;

public class Customer {

	private String name;
	private ArrayList<Rental> rentalList = new ArrayList<Rental>();

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Rental arg) {
		rentalList.add(arg);
	}

	public String getName() {
		return name;
	}

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		StringBuilder subString = new StringBuilder();

		subString.append("Rental Record for " + getName() + "\n");
		for (Rental rental : rentalList){
			double thisAmount = getThisAmount(rental);
			totalAmount += thisAmount;
			frequentRenterPoints += getFrequentRenterPoints(rental);
			subString.append("\t").append(rental.getMovie().getTitle()).append("\t").append(thisAmount).append("\n");
		}
		return getString(totalAmount, frequentRenterPoints, subString.toString());
	}

	private int getFrequentRenterPoints(Rental each) {
		int frequentRenterPoints = 1;
		if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE)
				&& each.getDaysRented() > 1)
			frequentRenterPoints++;
		return frequentRenterPoints;
	}

	private String getString(double totalAmount, int frequentRenterPoints, String result) {
		result += "Amount owed is " + totalAmount + "\n"
				+ "You earned " + frequentRenterPoints
				+ " frequent renter points";
		return result;
	}

	private double getThisAmount(Rental each) {
		double thisAmount = 0;
		switch (each.getMovie().getPriceCode()) {
		case Movie.REGULAR:
			thisAmount = each.getDaysRented() > 2 ? thisAmount + (each.getDaysRented() - 2) * 1.5 + 2: thisAmount + 2;
			break;
		case Movie.NEW_RELEASE:
			thisAmount += each.getDaysRented() * 3;
			break;
		case Movie.CHILDRENS:
			thisAmount = each.getDaysRented() > 3? thisAmount + (each.getDaysRented() - 3) * 1.5 + 1.5 : thisAmount + 1.5;
			break;
		}
		return thisAmount;
	}
}
