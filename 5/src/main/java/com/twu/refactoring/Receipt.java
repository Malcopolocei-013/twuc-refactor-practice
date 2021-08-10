package com.twu.refactoring;

public class Receipt {
    private static final int FIXED_CHARGE = 50;
    private static final double PEAK_TIME_MULTIPLIER = 1.2;
    private static final double OFF_PEAK_MULTIPLIER = 1.0;
    private static final int RATE_CHANGE_DISTANCE = 10;
    private static final int PRE_RATE_CHANGE_NON_AC_RATE = 15;
    private static final int POST_RATE_CHANGE_NON_AC_RATE = 12;
    private static final int PRE_RATE_CHANGE_AC_RATE = 20;
    private static final int POST_RATE_CHANGE_AC_RATE = 17;
    private static final double SALES_TAX_RATE = 0.1;

    private final Taxi taxi;

    public Receipt(Taxi taxi) {
        this.taxi = taxi;
    }

    public double getAirconditionedPreCost() {
        double totalCost = 0;

        totalCost += FIXED_CHARGE;
        int totalKms = taxi.getTotalKms();
        double peakTimeMultiple = taxi.isPeakTime() ? PEAK_TIME_MULTIPLIER : OFF_PEAK_MULTIPLIER;
        int PRE_RATE_CHANGE = taxi.isAirConditioned()? PRE_RATE_CHANGE_AC_RATE: PRE_RATE_CHANGE_NON_AC_RATE;
        int POST_RATE_CHANGE = taxi.isAirConditioned()? POST_RATE_CHANGE_AC_RATE: POST_RATE_CHANGE_NON_AC_RATE;

        /*计算价格 = 里程数 * 计价率（分前后） * 是否黄金时间段*/
        totalCost += Math.min(RATE_CHANGE_DISTANCE, totalKms) * PRE_RATE_CHANGE * peakTimeMultiple;
        totalCost += Math.max(totalKms - RATE_CHANGE_DISTANCE, 0) * POST_RATE_CHANGE * peakTimeMultiple;
        return totalCost * (1 + SALES_TAX_RATE);
    }


}
