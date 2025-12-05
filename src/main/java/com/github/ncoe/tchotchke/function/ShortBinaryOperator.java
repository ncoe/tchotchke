/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.function;

/**
 * Represents an operation upon two {@code short}-valued operands and producing an {@code short}-valued result.
 * This is the primitive type specialization of {@link java.util.function.BinaryOperator} for {@code short}.
 */
@FunctionalInterface
public interface ShortBinaryOperator {
    /**
     * Applies this operator to the given operands.
     *
     * @param left  the first operand
     * @param right the second operand
     * @return the operator result
     */
    short applyAsShort(short left, short right);
}
