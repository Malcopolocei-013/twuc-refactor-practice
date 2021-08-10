package com.twu.refactoring;

import java.util.Arrays;

public class NumberCruncher {
    public static final int EVEN = 0;
    public static final int ODD = 1;
    private final int[] numbers;


    public NumberCruncher(int... numbers) {
        this.numbers = numbers;
    }

    public int countEven() {
        return (int)Arrays.stream(numbers).filter(number -> number % 2 == EVEN).count();
    }

    public int countOdd() {
        return (int)Arrays.stream(numbers).filter(number -> number % 2 == ODD).count();
    }

    public int countPositive() {
        int count = 0;
        for (int number : numbers) {
            if (number >= 0) count++;
        }
        return count;
    }

    public int countNegative() {
        int count = 0;
        for (int number : numbers) {
            if (number < 0) count++;
        }
        return count;
    }
}
