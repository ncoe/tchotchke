/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.function;

import com.github.ncoe.tchotchke.util.Assertion;

/**
 * Represents an operation on a single {@code char}-valued operand that produces an {@code char}-valued result.
 * This is the primitive type specialization of {@link java.util.function.UnaryOperator} for {@code char}.
 */
@FunctionalInterface
public interface CharUnaryOperator {
    /**
     * Applies this operator to the given operand.
     *
     * @param operand the operand
     * @return the operator result
     */
    char applyAsChar(char operand);

    /**
     * Returns a composed operator that first applies the {@code before} operator to its input,
     * and then applies this operator to the result. If evaluation of either operator throws an exception,
     * it is relayed to the caller of the composed operator.
     *
     * @param before the operator to apply before this operator is applied
     * @return a composed operator that first applies the {@code before} operator and then applies this operator
     */
    default CharUnaryOperator compose(CharUnaryOperator before) {
        Assertion.notNull(before, "before may not be null");
        return (char ch) -> applyAsChar(before.applyAsChar(ch));
    }

    /**
     * Returns a composed operator that first applies this operator to its input,
     * and then applies the {@code after} operator to the result.
     * If evaluation of either operator throws an exception, it is relayed to the caller of the composed operator.
     *
     * @param after the operator to apply after this operator is applied
     * @return a composed operator that first applies this operator and then applies the {@code after} operator
     */
    default CharUnaryOperator andThen(CharUnaryOperator after) {
        Assertion.notNull(after, "after may not be null");
        return (char ch) -> after.applyAsChar(applyAsChar(ch));
    }

    /**
     * Returns a unary operator that always returns its input argument.
     *
     * @return a unary operator that always returns its input argument
     */
    static CharUnaryOperator identity() {
        return t -> t;
    }
}
