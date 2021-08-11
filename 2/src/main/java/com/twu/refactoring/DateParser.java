package com.twu.refactoring;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

public class DateParser {
    public static final String YEAR_STRING_IS_LESS_THAN_4_CHARACTERS = "Year string is less than 4 characters";
    public static final String YEAR_IS_NOT_AN_INTEGER = "Year is not an integer";
    public static final String YEAR_CANNOT_BE_LESS_THAN_2000_OR_MORE_THAN_2012 = "Year cannot be less than 2000 or more than 2012";
    public static final String MONTH_STRING_IS_LESS_THAN_2_CHARACTERS = "Month string is less than 2 characters";
    public static final String MONTH_IS_NOT_AN_INTEGER = "Month is not an integer";
    public static final String MONTH_CANNOT_BE_LESS_THAN_1_OR_MORE_THAN_12 = "Month cannot be less than 1 or more than 12";
    public static final String DATE_STRING_IS_LESS_THAN_2_CHARACTERS = "Date string is less than 2 characters";
    public static final String DATE_IS_NOT_AN_INTEGER = "Date is not an integer";
    public static final String DATE_CANNOT_BE_LESS_THAN_1_OR_MORE_THAN_31 = "Date cannot be less than 1 or more than 31";
    public static final String HOUR_STRING_IS_LESS_THAN_2_CHARACTERS = "Hour string is less than 2 characters";
    public static final String HOUR_IS_NOT_AN_INTEGER = "Hour is not an integer";
    public static final String HOUR_CANNOT_BE_LESS_THAN_0_OR_MORE_THAN_23 = "Hour cannot be less than 0 or more than 23";
    public static final String MINUTE_STRING_IS_LESS_THAN_2_CHARACTERS = "Minute string is less than 2 characters";
    public static final String MINUTE_IS_NOT_AN_INTEGER = "Minute is not an integer";
    public static final String MINUTE_CANNOT_BE_LESS_THAN_0_OR_MORE_THAN_59 = "Minute cannot be less than 0 or more than 59";
    private final String dateAndTimeString;
    private static final HashMap<String, TimeZone> KNOWN_TIME_ZONES = new HashMap<String, TimeZone>();

    static {
        KNOWN_TIME_ZONES.put("UTC", TimeZone.getTimeZone("UTC"));
    }

    /**
     * Takes a date in ISO 8601 format and returns a date
     *
     * @param dateAndTimeString - should be in format ISO 8601 format
     *                          examples -
     *                          2012-06-17 is 17th June 2012 - 00:00 in UTC TimeZone
     *                          2012-06-17TZ is 17th June 2012 - 00:00 in UTC TimeZone
     *                          2012-06-17T15:00Z is 17th June 2012 - 15:00 in UTC TimeZone
     */
    public DateParser(String dateAndTimeString) {
        this.dateAndTimeString = dateAndTimeString;
    }

    public int stringConverter(int starIndex, int endIndex){
        return Integer.parseInt(dateAndTimeString.substring(starIndex, endIndex));
    }

    public void boundJudge(int needToJudge, int leftBound, int rightBound, String message){
        if (needToJudge < 2000 || needToJudge >2012)
            throw new IllegalArgumentException(message);
    }

    public Date parse() {
        int year, month, date, hour, minute;

        try {
            year = stringConverter(0, 4);
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(YEAR_STRING_IS_LESS_THAN_4_CHARACTERS);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(YEAR_IS_NOT_AN_INTEGER);
        }
        boundJudge(year, 2000, 2012, YEAR_CANNOT_BE_LESS_THAN_2000_OR_MORE_THAN_2012);

        try {
            month = stringConverter(5,7);
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(MONTH_STRING_IS_LESS_THAN_2_CHARACTERS);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(MONTH_IS_NOT_AN_INTEGER);
        }
        boundJudge(month, 1, 12, MONTH_CANNOT_BE_LESS_THAN_1_OR_MORE_THAN_12);

        try {
            date = stringConverter(8,10);
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(DATE_STRING_IS_LESS_THAN_2_CHARACTERS);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(DATE_IS_NOT_AN_INTEGER);
        }
        //可以把这个抽成一个超长的方法，同时判断两种错误，同时合并之前的stringConverter
        boundJudge(date, 1, 31, DATE_CANNOT_BE_LESS_THAN_1_OR_MORE_THAN_31);

        if (dateAndTimeString.substring(11, 12).equals("Z")) {
            hour = 0;
            minute = 0;
        } else {
            try {
                hour = stringConverter(11, 13);
            } catch (StringIndexOutOfBoundsException e) {
                throw new IllegalArgumentException(HOUR_STRING_IS_LESS_THAN_2_CHARACTERS);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(HOUR_IS_NOT_AN_INTEGER);
            }
            if (hour < 0 || hour > 23)
                throw new IllegalArgumentException(HOUR_CANNOT_BE_LESS_THAN_0_OR_MORE_THAN_23);

            try {
                minute = stringConverter(14, 16);
            } catch (StringIndexOutOfBoundsException e) {
                throw new IllegalArgumentException(MINUTE_STRING_IS_LESS_THAN_2_CHARACTERS);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(MINUTE_IS_NOT_AN_INTEGER);
            }
            if (minute < 0 || minute > 59)
                throw new IllegalArgumentException(MINUTE_CANNOT_BE_LESS_THAN_0_OR_MORE_THAN_59);
        }

        Calendar calendar = Calendar.getInstance();
        calendarSet(year, month, date, hour, minute, calendar);
        return calendar.getTime();
    }

    private void calendarSet(int year, int month, int date, int hour, int minute, Calendar calendar) {
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        calendar.set(year, month - 1, date, hour, minute, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }
}
