/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.tuple;

/**
 * A tuple of three values
 *
 * @param x   the first value
 * @param y   the second value
 * @param z   the third value
 * @param <X> the first type
 * @param <Y> the second type
 * @param <Z> the third type
 */
public record Triple<X, Y, Z>(X x, Y y, Z z) {
    //empty
}
