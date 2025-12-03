/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.function;

import com.github.ncoe.tchotchke.util.Assertion;

import java.util.function.Predicate;

/**
 * Represents a predicate (boolean-valued function) of one {@code char}-valued argument.
 * This is the {@code char}-consuming primitive type specialization of {@link Predicate}.
 */
@FunctionalInterface
public interface CharacterPredicate {
    /**
     * Evaluates this predicate on the given argument.
     *
     * @param ch the character
     * @return {@code true} if the input argument matches the predicate, otherwise {@code false}
     */
    boolean test(char ch);

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
    default CharacterPredicate and(CharacterPredicate other) {
        Assertion.notNull(other, "the other predicate may not be null");
        return value -> test(value) && other.test(value);
    }

    /**
     * Returns a predicate that represents the logical negation of this predicate.
     *
     * @return a predicate that represents the logical negation of this predicate
     */
    default CharacterPredicate negate() {
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
    default CharacterPredicate or(CharacterPredicate other) {
        Assertion.notNull(other, "the other predicate may not be null");
        return value -> test(value) || other.test(value);
    }

    /**
     * Create a predicate to match a specific character.
     *
     * @param value the value to match
     * @return the predicate
     */
    static CharacterPredicate of(char value) {
        return of((int) value);
    }

    /**
     * Create a predicate to match a specific code point.
     *
     * @param value the value to match
     * @return the predicate
     */
    static CharacterPredicate of(int value) {
        return ch -> ch == value;
    }

    /**
     * Create a predicate to matcha range of characters.
     *
     * @param beg the beginning code point
     * @param end the ending code point
     * @return the predicate
     */
    static CharacterPredicate inclusive(char beg, char end) {
        return inclusive((int) beg, (int) end);
    }

    /**
     * Create a predicate to matcha range of code points.
     *
     * @param beg the beginning code point
     * @param end the ending code point
     * @return the predicate
     */
    static CharacterPredicate inclusive(int beg, int end) {
        return ch -> beg <= ch && ch <= end;
    }

    /**
     * Create a predicate that matches all characters
     *
     * @return the predicate
     */
    static CharacterPredicate any() {
        return _ -> true;
    }
}
