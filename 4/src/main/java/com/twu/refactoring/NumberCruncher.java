package com.twu.refactoring;

import java.util.Arrays;

public class NumberCruncher {
    public static final int EVEN = 0;
    private final int[] numbers;


    public NumberCruncher(int... numbers) {
        this.numbers = numbers;
    }

    public int countEven() {
        return (int)Arrays.stream(numbers).filter(number -> number % 2 == EVEN).count();
    }

    public int countOdd() {
        int count = 0;
        for (int number : numbers) {
            if (number % 2 == 1) count++;
        }
        return count;
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
