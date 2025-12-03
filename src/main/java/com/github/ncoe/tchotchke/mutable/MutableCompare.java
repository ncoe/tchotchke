/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.mutable;

/**
 * A mutable object that can be compared
 *
 * @param <T> the type of object
 */
public final class MutableCompare<T extends Comparable<T>> extends Mutable<T> implements Comparable<MutableCompare<T>> {
    /**
     * Constructor
     */
    public MutableCompare() {
        this(null);
    }

    /**
     * Constructor
     *
     * @param value the initial value
     */
    public MutableCompare(T value) {
        super(value);
    }

    @Override
    public int compareTo(MutableCompare<T> other) {
        if (other == null) {
            return 1;
        }
        if (get() == null) {
            if (other.get() == null) {
                return 0;
            }
            return -1;
        }
        return get().compareTo(other.get());
    }
}
