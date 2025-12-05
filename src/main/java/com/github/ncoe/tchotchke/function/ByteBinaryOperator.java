/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.function;

/**
 * Represents an operation upon two {@code byte}-valued operands and producing an {@code byte}-valued result.
 * This is the primitive type specialization of {@link java.util.function.BinaryOperator} for {@code byte}.
 */
@FunctionalInterface
public interface ByteBinaryOperator {
    /**
     * Applies this operator to the given operands.
     *
     * @param left  the first operand
     * @param right the second operand
     * @return the operator result
     */
    byte applyAsByte(byte left, byte right);
}
