/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.lex;

/**
 * Lex Action
 */
public enum LexAction {
    /**
     * Defer considering the character until after the state transition.
     */
    DEFER,
    /**
     * The current text is a token. Reset before adding the character.
     */
    REDUCE,
    /**
     * Append the character.
     */
    SHIFT,
    /**
     * Append the character, and build the token.
     */
    SHIFT_REDUCE,
    /**
     * Do not append the character.
     */
    SKIP,
    /**
     * Do not append the character, and build the token.
     */
    SKIP_REDUCE,
}
