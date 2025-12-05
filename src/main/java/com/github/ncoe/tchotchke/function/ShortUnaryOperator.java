/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.function;

import com.github.ncoe.tchotchke.util.Assertion;

/**
 * Represents an operation on a single {@code short}-valued operand that produces an {@code short}-valued result.
 * This is the primitive type specialization of {@link java.util.function.UnaryOperator} for {@code short}.
 */
@FunctionalInterface
public interface ShortUnaryOperator {
    /**
     * Applies this operator to the given operand.
     *
     * @param operand the operand
     * @return the operator result
     */
    short applyAsShort(short operand);

    /**
     * Returns a composed operator that first applies the {@code before} operator to its input,
     * and then applies this operator to the result. If evaluation of either operator throws an exception,
     * it is relayed to the caller of the composed operator.
     *
     * @param before the operator to apply before this operator is applied
     * @return a composed operator that first applies the {@code before} operator and then applies this operator
     */
    default ShortUnaryOperator compose(ShortUnaryOperator before) {
        Assertion.notNull(before, "before may not be null");
        return (short s) -> applyAsShort(before.applyAsShort(s));
    }

    /**
     * Returns a composed operator that first applies this operator to its input,
     * and then applies the {@code after} operator to the result.
     * If evaluation of either operator throws an exception, it is relayed to the caller of the composed operator.
     *
     * @param after the operator to apply after this operator is applied
     * @return a composed operator that first applies this operator and then applies the {@code after} operator
     */
    default ShortUnaryOperator andThen(ShortUnaryOperator after) {
        Assertion.notNull(after, "after may not be null");
        return (short s) -> after.applyAsShort(applyAsShort(s));
    }

    /**
     * Returns a unary operator that always returns its input argument.
     *
     * @return a unary operator that always returns its input argument
     */
    static ShortUnaryOperator identity() {
        return t -> t;
    }
}
