/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.mutable;

import com.github.ncoe.tchotchke.property.Property;

/**
 * A mutable object
 *
 * @param <T> the type of value
 */
public sealed class Mutable<T> implements Property<T> permits MutableCompare {
    private T value;

    /**
     * Constructor
     */
    public Mutable() {
        this(null);
    }

    /**
     * Constructor
     *
     * @param value the initial value
     */
    public Mutable(T value) {
        this.value = value;
    }

    @Override
    public final T get() {
        return this.value;
    }

    @Override
    public final void accept(T value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        if (this.value != null) {
            return this.value.hashCode();
        }
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this.value == null) {
            return obj == null;
        }
        if (obj instanceof Mutable<?> m) {
            return this.value.equals(m.value);
        }
        return super.equals(obj);
    }

    @Override
    public String toString() {
        if (this.value != null) {
            return this.value.toString();
        }
        return super.toString();
    }
}
