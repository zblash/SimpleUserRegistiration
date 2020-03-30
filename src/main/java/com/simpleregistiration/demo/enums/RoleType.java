package com.simpleregistiration.demo.enums;

public enum RoleType {
    ADMIN("ADMIN", "Admin"),
    USER("USER", "User");

    private String firstValue;
    private String secondValue;

    RoleType(String firstValue, String secondValue) {
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