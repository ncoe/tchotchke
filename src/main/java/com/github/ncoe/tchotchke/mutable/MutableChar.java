/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.mutable;

import com.github.ncoe.tchotchke.property.PropertyChar;

/**
 * A mutable char
 */
public final class MutableChar implements PropertyChar, Comparable<MutableChar> {
    private char value;

    /**
     * Constructor
     *
     * @param value the initial value
     */
    public MutableChar(char value) {
        this.value = value;
    }

    @Override
    public char getAsChar() {
        return this.value;
    }

    @Override
    public void accept(char value) {
        this.value = value;
    }

    @Override
    public int compareTo(MutableChar lhs) {
        return Character.compare(this.value, lhs.getAsChar());
    }

    @Override
    public int hashCode() {
        return this.value;
    }

    @Override
    public boolean equals(Object obj) {
        return switch (obj) {
            case Character ch -> this.value == ch;
            case MutableChar lhs -> this.value == lhs.getAsChar();
            case null, default -> false;
        };
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }
}
