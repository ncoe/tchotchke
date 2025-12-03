/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.mutable;

import com.github.ncoe.tchotchke.property.PropertyBool;

/**
 * A mutable boolean
 */
public final class MutableBool implements PropertyBool, Comparable<MutableBool> {
    private boolean value;

    /**
     * Constructor
     */
    public MutableBool() {
        this(false);
    }

    /**
     * Constructor
     *
     * @param value the initial value
     */
    public MutableBool(boolean value) {
        this.value = value;
    }

    @Override
    public boolean getAsBoolean() {
        return this.value;
    }

    @Override
    public void accept(boolean value) {
        this.value = value;
    }

    @Override
    public int compareTo(MutableBool lhs) {
        return Boolean.compare(this.value, lhs.getAsBoolean());
    }

    @Override
    public int hashCode() {
        return Boolean.hashCode(this.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Boolean lhs) {
            return this.value == lhs;
        }
        if (obj instanceof MutableBool lhs) {
            return this.value == lhs.getAsBoolean();
        }
        return false;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    /**
     * Is False
     *
     * @return is false
     */
    public boolean isFalse() {
        return !this.value;
    }

    /**
     * Set false
     */
    public void setFalse() {
        accept(false);
    }

    /**
     * Is True
     *
     * @return is true
     */
    public boolean isTrue() {
        return this.value;
    }

    /**
     * Set true
     */
    public void setTrue() {
        accept(true);
    }

    /**
     * Not
     *
     * @return the negated value
     */
    public boolean not() {
        return !this.value;
    }

    /**
     * Logical And
     *
     * @param lhs the left-hand side
     * @return the result
     */
    public boolean logicalAnd(boolean lhs) {
        return Boolean.logicalAnd(this.value, lhs);
    }

    /**
     * Logical And
     *
     * @param lhs the left-hand side
     * @return the result
     */
    public boolean logicalAnd(MutableBool lhs) {
        return Boolean.logicalAnd(this.value, lhs.getAsBoolean());
    }

    /**
     * Logical Or
     *
     * @param lhs the left-hand side
     * @return the result
     */
    public boolean logicalOr(boolean lhs) {
        return Boolean.logicalOr(this.value, lhs);
    }

    /**
     * Logical Or
     *
     * @param lhs the left-hand side
     * @return the result
     */
    public boolean logicalOr(MutableBool lhs) {
        return Boolean.logicalOr(this.value, lhs.getAsBoolean());
    }

    /**
     * Logical Xor
     *
     * @param lhs the left-hand side
     * @return the result
     */
    public boolean logicalXor(boolean lhs) {
        return Boolean.logicalXor(this.value, lhs);
    }

    /**
     * Logical Xor
     *
     * @param lhs the left-hand side
     * @return the result
     */
    public boolean logicalXor(MutableBool lhs) {
        return Boolean.logicalXor(this.value, lhs.getAsBoolean());
    }
}
