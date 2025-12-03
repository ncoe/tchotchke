/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.property;

import com.github.ncoe.tchotchke.function.CharacterConsumer;
import com.github.ncoe.tchotchke.function.CharacterSupplier;

/**
 * A character property with a getter and setter.
 * See {@link CharacterSupplier} for a standalone getter.
 * See {@link CharacterConsumer} for a standalone setter.
 */
public interface PropertyChar extends CharacterSupplier, CharacterConsumer {
    //empty
}
