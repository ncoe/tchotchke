/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.property;

import java.util.function.IntConsumer;
import java.util.function.IntSupplier;

/**
 * An integer property with a getter and setter.
 * See {@link IntSupplier} for a standalone getter.
 * See {@link IntConsumer} for a standalone setter.
 */
public interface PropertyInt extends IntSupplier, IntConsumer {
    //empty
}
