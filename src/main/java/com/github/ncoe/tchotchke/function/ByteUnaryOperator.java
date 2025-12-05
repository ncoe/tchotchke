/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.function;

import com.github.ncoe.tchotchke.util.Assertion;

/**
 * Represents an operation on a single {@code byte}-valued operand that produces an {@code byte}-valued result.
 * This is the primitive type specialization of {@link java.util.function.UnaryOperator} for {@code byte}.
 */
@FunctionalInterface
public interface ByteUnaryOperator {
    /**
     * Applies this operator to the given operand.
     *
     * @param operand the operand
     * @return the operator result
     */
    byte applyAsByte(byte operand);

    /**
     * Returns a composed operator that first applies the {@code before} operator to its input,
     * and then applies this operator to the result. If evaluation of either operator throws an exception,
     * it is relayed to the caller of the composed operator.
     *
     * @param before the operator to apply before this operator is applied
     * @return a composed operator that first applies the {@code before} operator and then applies this operator
     */
    default ByteUnaryOperator compose(ByteUnaryOperator before) {
        Assertion.notNull(before, "before may not be null");
        return (byte b) -> applyAsByte(before.applyAsByte(b));
    }

    /**
     * Returns a composed operator that first applies this operator to its input,
     * and then applies the {@code after} operator to the result.
     * If evaluation of either operator throws an exception, it is relayed to the caller of the composed operator.
     *
     * @param after the operator to apply after this operator is applied
     * @return a composed operator that first applies this operator and then applies the {@code after} operator
     */
    default ByteUnaryOperator andThen(ByteUnaryOperator after) {
        Assertion.notNull(after, "after may not be null");
        return (byte b) -> after.applyAsByte(applyAsByte(b));
    }

    /**
     * Returns a unary operator that always returns its input argument.
     *
     * @return a unary operator that always returns its input argument
     */
    static ByteUnaryOperator identity() {
        return t -> t;
    }
}
