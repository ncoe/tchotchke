/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.util;

/**
 * Runtime assertions
 */
public final class Assertion {
    private Assertion() {
        //empty
    }

    /**
     * Verify a condition is false
     *
     * @param condition the condition
     * @param format    the format
     * @param args      the arguments
     */
    public static void isFalse(boolean condition, String format, Object... args) {
        if (condition) {
            throw new IllegalArgumentException(format.formatted(args));
        }
    }

    /**
     * Verify a condition is true
     *
     * @param condition the condition
     * @param format    the format
     * @param args      the arguments
     */
    public static void isTrue(boolean condition, String format, Object... args) {
        if (!condition) {
            throw new IllegalArgumentException(format.formatted(args));
        }
    }

    /**
     * Verify a value is null
     *
     * @param value  the value
     * @param format the format
     * @param args   the arguments
     * @param <T>    the type of value
     */
    public static <T> void isNull(T value, String format, Object... args) {
        if (value != null) {
            throw new IllegalArgumentException(format.formatted(args));
        }
    }

    /**
     * Verify a value is not null
     *
     * @param value  the value
     * @param format the format
     * @param args   the arguments
     * @param <T>    the type of value
     */
    public static <T> void notNull(T value, String format, Object... args) {
        if (value == null) {
            throw new IllegalArgumentException(format.formatted(args));
        }
    }
}
