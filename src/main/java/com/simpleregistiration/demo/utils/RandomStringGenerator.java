package com.simpleregistiration.demo.utils;

import java.util.Random;
import java.util.UUID;

public final class RandomStringGenerator {

    public static String generateId() {
        return UUID.randomUUID().toString();
    }

    public static String generateNumberString() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }
}