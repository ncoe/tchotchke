/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.function;

/**
 * Represents a function that accepts a {@code short}-valued argument and produces a result.
 * This is the {@code short}-consuming primitive specialization for {@link java.util.function.Function}.
 *
 * @param <R> the type of the result of the function
 */
@FunctionalInterface
public interface ShortFunction<R> {
    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    R apply(short value);
}
