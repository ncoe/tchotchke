/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.property;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * A property with a getter and setter
 * See {@link Supplier} for a standalone getter.
 * See {@link Consumer} for a standalone setter.
 *
 * @param <T> the type of property
 */
public interface Property<T> extends Supplier<T>, Consumer<T> {
    //empty
}
