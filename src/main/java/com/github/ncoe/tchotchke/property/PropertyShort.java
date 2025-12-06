/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.property;

import com.github.ncoe.tchotchke.function.ShortConsumer;
import com.github.ncoe.tchotchke.function.ShortSupplier;

/**
 * An integer property with a getter and setter.
 * See {@link ShortSupplier} for a standalone getter.
 * See {@link ShortConsumer} for a standalone setter.
 */
public interface PropertyShort extends ShortSupplier, ShortConsumer {
    //empty
}
