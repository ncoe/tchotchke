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

    /**
     * Check if two references are the same
     *
     * @param lhs    the left value
     * @param rhs    the right value
     * @param format the format
     * @param args   the arguments
     * @param <T>    the type of value
     */
    public static <T> void isSame(T lhs, T rhs, String format, Object... args) {
        if (lhs != rhs) {
            throw new IllegalArgumentException(format.formatted(args));
        }
    }

    /**
     * Check if two references are not the same
     *
     * @param lhs    the left value
     * @param rhs    the right value
     * @param format the format
     * @param args   the arguments
     * @param <T>    the type of value
     */
    public static <T> void isNotSame(T lhs, T rhs, String format, Object... args) {
        if (lhs == rhs) {
            throw new IllegalArgumentException(format.formatted(args));
        }
    }

    /**
     * Check if two values are equal
     *
     * @param lhs    the left value
     * @param rhs    the right value
     * @param format the format
     * @param args   the arguments
     * @param <T>    the type of value
     */
    public static <T> void isEqual(T lhs, T rhs, String format, Object... args) {
        if (lhs == null) {
            if (rhs != null) {
                throw new IllegalArgumentException(format.formatted(args));
            }
        } else {
            if (!lhs.equals(rhs)) {
                throw new IllegalArgumentException(format.formatted(args));
            }
        }
    }

    /**
     * Check if two values are not equal
     *
     * @param lhs    the left value
     * @param rhs    the right value
     * @param format the format
     * @param args   the arguments
     * @param <T>    the type of value
     */
    public static <T> void isNotEqual(T lhs, T rhs, String format, Object... args) {
        if (lhs == null) {
            if (rhs == null) {
                throw new IllegalArgumentException(format.formatted(args));
            }
        } else {
            if (lhs.equals(rhs)) {
                throw new IllegalArgumentException(format.formatted(args));
            }
        }
    }

    /**
     * Check if a value is less than another
     *
     * @param lhs    the left value
     * @param rhs    the right value
     * @param format the format
     * @param args   the arguments
     * @param <T>    the type of value
     */
    public static <T extends Comparable<T>> void isLess(T lhs, T rhs, String format, Object... args) {
        //lhs < rhs
        if (rhs == null || lhs != null && lhs.compareTo(rhs) >= 0) {
            throw new IllegalArgumentException(format.formatted(args));
        }
    }

    /**
     * Check if a value is less than or equal to another
     *
     * @param lhs    the left value
     * @param rhs    the right value
     * @param format the format
     * @param args   the arguments
     * @param <T>    the type of value
     */
    public static <T extends Comparable<T>> void isLessOrEqual(T lhs, T rhs, String format, Object... args) {
        //lhs <= rhs
        if (rhs == null) {
            if (lhs != null) {
                throw new IllegalArgumentException(format.formatted(args));
            }
        } else {
            if (lhs != null && lhs.compareTo(rhs) > 0) {
                throw new IllegalArgumentException(format.formatted(args));
            }
        }
    }

    /**
     * Check if a value is greater than another
     *
     * @param lhs    the left value
     * @param rhs    the right value
     * @param format the format
     * @param args   the arguments
     * @param <T>    the type of value
     */
    public static <T extends Comparable<T>> void isGreater(T lhs, T rhs, String format, Object... args) {
        //lhs > rhs
        if (lhs == null || rhs != null && lhs.compareTo(rhs) <= 0) {
            throw new IllegalArgumentException(format.formatted(args));
        }
    }

    /**
     * Check if a value is greater than another
     *
     * @param lhs    the left value
     * @param rhs    the right value
     * @param format the format
     * @param args   the arguments
     * @param <T>    the type of value
     */
    public static <T extends Comparable<T>> void isGreaterOrEqual(T lhs, T rhs, String format, Object... args) {
        //lhs >= rhs
        if (lhs == null) {
            if (rhs != null) {
                throw new IllegalArgumentException(format.formatted(args));
            }
        } else {
            if (rhs != null && lhs.compareTo(rhs) < 0) {
                throw new IllegalArgumentException(format.formatted(args));
            }
        }
    }
}
