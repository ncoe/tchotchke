/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.function;

/**
 * Represents a function that accepts a {@code boolean}-valued argument and produces a result.
 * This is the {@code boolean}-consuming primitive specialization for {@link java.util.function.Function}.
 *
 * @param <R> the type of the result of the function
 */
@FunctionalInterface
public interface BooleanFunction<R> {
    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    R apply(boolean value);
}
