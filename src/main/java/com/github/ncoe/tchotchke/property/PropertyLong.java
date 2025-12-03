/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.property;

import java.util.function.LongConsumer;
import java.util.function.LongSupplier;

/**
 * A long property with a getter and setter.
 * See {@link LongSupplier} for a standalone getter.
 * See {@link LongConsumer} for a standalone setter.
 */
public interface PropertyLong extends LongSupplier, LongConsumer {
    //empty
}
