/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.function;

import com.github.ncoe.tchotchke.util.Assertion;

/**
 * A tagged union for a value or error
 *
 * @param <T> the type of value
 * @param <E> the type of error
 */
@SuppressWarnings("unused")
public sealed interface Result<T, E> {
    /**
     * A successful result
     *
     * @param value the value
     * @param <T>   the type of value
     * @param <E>   the type of error
     */
    record Ok<T, E>(T value) implements Result<T, E> {
        //empty
    }

    /**
     * A successful result
     *
     * @param value the value
     * @param <T>   the type of value
     * @param <E>   the type of error
     * @return the result
     */
    static <T, E> Result<T, E> ok(T value) {
        Assertion.notNull(value, "value is required");
        return new Ok<>(value);
    }

    /**
     * An error result
     *
     * @param error the error
     * @param <T>   the type of value
     * @param <E>   the type of error
     */
    record Error<T, E>(E error) implements Result<T, E> {
        //empty
    }

    /**
     * An error result
     *
     * @param error the error
     * @param <T>   the type of value
     * @param <E>   the type of error
     * @return the result
     */
    static <T, E> Result<T, E> error(E error) {
        Assertion.notNull(error, "error is required");
        return new Error<>(error);
    }
}
