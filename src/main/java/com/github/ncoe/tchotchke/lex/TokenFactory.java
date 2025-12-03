/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.lex;

/**
 * Token Factory
 *
 * @param <T> the type of token
 */
@FunctionalInterface
public interface TokenFactory<T> {
    /**
     * Create a token given the state that produced it, and the text produced.
     *
     * @param state the state name
     * @param text  the text
     * @return the token
     */
    T invoke(String state, String text);
}
