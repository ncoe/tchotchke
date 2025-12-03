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
public final class Mutable<T> implements Property<T> {
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
    public T get() {
        return this.value;
    }

    @Override
    public void accept(T value) {
        this.value = value;
    }
}
