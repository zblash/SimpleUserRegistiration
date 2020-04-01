package com.simpleregistiration.demo.enums;

public enum DateFilter {
    LASTDAY("LASTDAY", "Last Day"),
    LASTWEEK("LASTWEEK", "Last Week"),
    LASTMONTH("LASTMONTH", "Last Month");

    private String firstValue;
    private String secondValue;

    DateFilter(String firstValue, String secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public String getFirstValue() {
        return firstValue;
    }

    public String getSecondValue() {
        return secondValue;
    }

    @Override
    public String toString() {
        return String.valueOf(firstValue);
    }


}
