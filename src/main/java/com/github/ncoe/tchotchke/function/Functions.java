/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.function;

/**
 * Functions
 */
public final class Functions {
    private static final Runnable DO_NOTHING = () -> {
        //empty
    };

    private Functions() {
        //empty
    }

    /**
     * Name a runnable that does nothing
     *
     * @return the runnable
     */
    public static Runnable doNothing() {
        return DO_NOTHING;
    }
}
