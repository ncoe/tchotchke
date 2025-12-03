/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.lex;

/**
 * Lex Exception
 */
public final class LexException extends RuntimeException {
    /**
     * Constructor
     *
     * @param format the format
     * @param args   the args
     */
    public LexException(String format, Object... args) {
        super(format.formatted(args));
    }
}
