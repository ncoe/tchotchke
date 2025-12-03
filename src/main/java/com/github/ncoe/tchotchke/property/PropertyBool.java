/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.property;

import com.github.ncoe.tchotchke.function.BooleanConsumer;

import java.util.function.BooleanSupplier;

/**
 * A boolean property with a getter and setter.
 * See {@link BooleanSupplier} for a standalone getter.
 * See {@link BooleanConsumer} for a standalone setter.
 */
public interface PropertyBool extends BooleanSupplier, BooleanConsumer {
    //empty
}
