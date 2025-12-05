/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.function;

/**
 * Represents a supplier of bytes.
 */
@FunctionalInterface
public interface ByteSupplier {
    /**
     * Get a byte
     *
     * @return the byte
     */
    byte getAsByte();
}
