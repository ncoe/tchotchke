/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.function;

/**
 * Represents a supplier of shorts.
 */
@FunctionalInterface
public interface ShortSupplier {
    /**
     * Get a short
     *
     * @return the short
     */
    short getAsShort();
}
