package com.twu.refactoring;

import java.util.Arrays;

public class NumberCruncher {
    public static final int EVEN = 0;
    public static final int ODD = 1;
    private final int[] numbers;


    public NumberCruncher(int... numbers) {
        this.numbers = numbers;
    }

    public int countEvenOrOdd(int event) {
        return (int)Arrays.stream(numbers).filter(number -> number % 2 == event).count();
    }

    public int countEven() {
        return countEvenOrOdd(EVEN);
    }

    public int countOdd() {
        return countEvenOrOdd(ODD);
    }

    public int countPositive() {
        return (int) Arrays.stream(numbers).filter(number -> number >= 0).count();
    }
    public int countNegative() {
        return (int) Arrays.stream(numbers).filter(number -> number < 0).count();
    }
}
