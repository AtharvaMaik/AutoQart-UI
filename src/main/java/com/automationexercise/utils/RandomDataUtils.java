package com.automationexercise.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class RandomDataUtils {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

    private RandomDataUtils() {
    }

    public static String uniqueEmail(String prefix) {
        return prefix + "+" + LocalDateTime.now().format(FORMATTER) + "@example.com";
    }
}

