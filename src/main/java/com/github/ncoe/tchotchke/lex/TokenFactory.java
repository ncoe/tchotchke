/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.lex;

import com.github.ncoe.tchotchke.option.Option;

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
     * @param end   is the final token in the stream
     * @return the optional token
     */
    Option<T> invoke(String state, String text, boolean end);
}
