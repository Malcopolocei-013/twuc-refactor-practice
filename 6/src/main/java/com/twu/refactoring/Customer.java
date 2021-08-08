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

//		rentalList.stream().forEach( rental -> {
//			double thisAmount = 0;
//			thisAmount = getThisAmount(thisAmount, rental);
//
//			frequentRenterPoints = getFrequentRenterPoints(frequentRenterPoints, rental);
//
//
//		} );

		Iterator<Rental> rentals = rentalList.iterator();
		String result = "Rental Record for " + getName() + "\n";

		while (rentals.hasNext()) {
			double thisAmount = 0;
			Rental each = rentals.next();

			thisAmount = getThisAmount(thisAmount, each);

			// add frequent renter points
			frequentRenterPoints = getFrequentRenterPoints(frequentRenterPoints, each);
			// show figures for this rental
			result = getResult(result, thisAmount, each);
			totalAmount += thisAmount;
		}
		return getString(totalAmount, frequentRenterPoints, result);
	}

	private String getResult(String result, double thisAmount, Rental each) {
		result += "\t" + each.getMovie().getTitle() + "\t"
				+ thisAmount + "\n";
		return result;
	}

	private int getFrequentRenterPoints(int frequentRenterPoints, Rental each) {
		frequentRenterPoints++;
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

	private double getThisAmount(double thisAmount, Rental each) {
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
