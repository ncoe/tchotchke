/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.function;

import com.github.ncoe.tchotchke.util.Assertion;

import java.util.function.Predicate;

/**
 * Represents a predicate (byte-valued function) of one {@code byte}-valued argument.
 * This is the {@code byte}-consuming primitive type specialization of {@link Predicate}.
 */
@FunctionalInterface
public interface BytePredicate {
    /**
     * Evaluates this predicate on the given argument.
     *
     * @param b the byte
     * @return {@code true} if the input argument matches the predicate, otherwise {@code false}
     */
    boolean test(byte b);

    /**
     * Returns a composed predicate that represents a short-circuiting logical AND of this predicate and another.
     * When evaluating the composed predicate, if this predicate is {@code false},
     * then the {@code other} predicate is not evaluated.
     *
     * <p>Any exceptions thrown during evaluation of either predicate are relayed to the caller;
     * if evaluation of this predicate throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other a predicate that will be logically-ANDed with this predicate
     * @return a composed predicate that represents the short-circuiting
     * logical AND of this predicate and the {@code other} predicate
     */
    default BytePredicate and(BytePredicate other) {
        Assertion.notNull(other, "the other predicate may not be null");
        return value -> test(value) && other.test(value);
    }

    /**
     * Returns a predicate that represents the logical negation of this predicate.
     *
     * @return a predicate that represents the logical negation of this predicate
     */
    default BytePredicate negate() {
        return value -> !test(value);
    }

    /**
     * Returns a composed predicate that represents a short-circuiting logical OR of this predicate and another.
     * When evaluating the composed predicate, if this predicate is {@code true},
     * then the {@code other} predicate is not evaluated.
     *
     * <p>Any exceptions thrown during evaluation of either predicate are relayed to the caller;
     * if evaluation of this predicate throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other a predicate that will be logically-ORed with this predicate
     * @return a composed predicate that represents the short-circuiting
     * logical OR of this predicate and the {@code other} predicate
     */
    default BytePredicate or(BytePredicate other) {
        Assertion.notNull(other, "the other predicate may not be null");
        return value -> test(value) || other.test(value);
    }
}
