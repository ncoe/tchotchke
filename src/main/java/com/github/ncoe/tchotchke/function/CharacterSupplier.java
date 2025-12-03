/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.function;

/**
 * Represents a supplier of characters.
 */
@FunctionalInterface
public interface CharacterSupplier {
    /**
     * Get a character
     *
     * @return the character
     */
    char getAsChar();
}
