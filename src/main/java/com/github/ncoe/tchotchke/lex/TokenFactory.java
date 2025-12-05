/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.lex;

import com.github.ncoe.tchotchke.option.Option;

/**
 * Token Factory
 *
 * @param <S> the type of state
 * @param <T> the type of token
 */
@FunctionalInterface
public interface TokenFactory<S, T> {
    /**
     * Create a token given the state that produced it, and the text produced.
     *
     * @param state the state name
     * @param text  the text
     * @param end   is the final token in the stream
     * @return the optional token (must not be null)
     */
    Option<T> invoke(S state, String text, boolean end);
}
