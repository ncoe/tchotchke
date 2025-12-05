/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.function;

import com.github.ncoe.tchotchke.util.Assertion;

import java.util.function.Consumer;

/**
 * Represents an operation that accepts a single {@code byte}-valued argument and returns no result.
 * This is the primitive type specialization of {@link Consumer} for {@code byte}.
 * Unlike most other functional interfaces, {@code ByteConsumer} is expected to operate via side-effects.
 *
 * <p>This is a functional interface whose functional method is {@link #accept(byte)}.
 *
 * @see Consumer
 */
@FunctionalInterface
public interface ByteConsumer {
    /**
     * Performs this operation on the given argument.
     *
     * @param value the input argument
     */
    void accept(byte value);

    /**
     * Returns a composed {@code ByteConsumer} that performs, in sequence, this operation followed by the {@code after} operation.
     * If performing either operation throws an exception, it is relayed to the caller of the composed operation.
     * If performing this operation throws an exception, the {@code after} operation will not be performed.
     *
     * @param after the operation to perform after this operation
     * @return a composed {@code ByteConsumer} that performs in sequence this operation followed by the {@code after} operation
     * @throws NullPointerException if {@code after} is null
     */
    default ByteConsumer andThen(ByteConsumer after) {
        Assertion.notNull(after, "after is required");
        return (byte b) -> {
            accept(b);
            after.accept(b);
        };
    }
}
