/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.property;

import com.github.ncoe.tchotchke.function.ByteConsumer;
import com.github.ncoe.tchotchke.function.ByteSupplier;

/**
 * An integer property with a getter and setter.
 * See {@link ByteSupplier} for a standalone getter.
 * See {@link ByteConsumer} for a standalone setter.
 */
public interface PropertyByte extends ByteSupplier, ByteConsumer {
    //empty
}
