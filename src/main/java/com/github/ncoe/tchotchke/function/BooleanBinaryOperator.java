/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.function;

/**
 * Represents an operation upon two {@code boolean}-valued operands and producing an {@code boolean}-valued result.
 * This is the primitive type specialization of {@link java.util.function.BinaryOperator} for {@code boolean}.
 */
@FunctionalInterface
public interface BooleanBinaryOperator {
    /**
     * Applies this operator to the given operands.
     *
     * @param left  the first operand
     * @param right the second operand
     * @return the operator result
     */
    boolean applyAsBool(boolean left, boolean right);
}
