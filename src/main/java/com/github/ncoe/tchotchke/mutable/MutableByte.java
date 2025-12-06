/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.mutable;

import com.github.ncoe.tchotchke.function.ByteBinaryOperator;
import com.github.ncoe.tchotchke.property.PropertyByte;

/**
 * A mutable byte
 */
public final class MutableByte extends Number implements PropertyByte, Comparable<MutableByte> {
    private byte value;

    /**
     * Constructor
     */
    public MutableByte() {
        this((byte) 0);
    }

    /**
     * Constructor
     *
     * @param value the initial value
     */
    public MutableByte(byte value) {
        this.value = value;
    }

    @Override
    public int intValue() {
        return this.value;
    }

    @Override
    public long longValue() {
        return this.value;
    }

    @Override
    public float floatValue() {
        return this.value;
    }

    @Override
    public double doubleValue() {
        return this.value;
    }

    @Override
    public byte getAsByte() {
        return this.value;
    }

    @Override
    public void accept(byte value) {
        this.value = value;
    }

    @Override
    public int compareTo(MutableByte lhs) {
        return Integer.compare(this.value, lhs.intValue());
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(this.value);
    }

    @Override
    public boolean equals(Object obj) {
        return switch (obj) {
            case Byte i -> this.value == i;
            case MutableByte m -> this.value == m.getAsByte();
            case Number n -> this.value == n.byteValue();
            case null, default -> false;
        };
    }

    @Override
    public String toString() {
        return Byte.toString(this.value);
    }

    /**
     * Updates the current value, and returns it.
     *
     * @param op  the operator
     * @param lhs the left-hand side
     * @return the updated value
     */
    public byte accumulateAndGet(ByteBinaryOperator op, byte lhs) {
        this.value = op.applyAsByte(this.value, lhs);
        return this.value;
    }

    /**
     * Updates the current value, and returns it.
     *
     * @param lhs the left-hand side
     * @return the updated value
     */
    public byte addAndGet(byte lhs) {
        this.value += lhs;
        return this.value;
    }

    /**
     * Decrements the current value, and returns it.
     *
     * @return the updated value
     */
    public byte decrementAndGet() {
        return --this.value;
    }

    /**
     * Increments the current value, and returns it.
     *
     * @return the updated value
     */
    public byte incrementAndGet() {
        return ++this.value;
    }

    /**
     * Updates the current value, returns the old value.
     *
     * @param op  the operator
     * @param lhs the left=-hand side
     * @return the original value
     */
    public byte getAndAccumulate(ByteBinaryOperator op, byte lhs) {
        byte oldValue = this.value;
        this.value = op.applyAsByte(this.value, lhs);
        return oldValue;
    }

    /**
     * Updates the current value, returns the old value.
     *
     * @param lhs the left-hand side
     * @return the original value
     */
    public byte getAndAdd(byte lhs) {
        byte oldValue = this.value;
        this.value += lhs;
        return oldValue;
    }

    /**
     * Updates the current value, returns the old value.
     *
     * @return the original value
     */
    public byte getAndDecrement() {
        return this.value--;
    }

    /**
     * Updates the current value, returns the old value.
     *
     * @return the original value
     */
    public byte getAndIncrement() {
        return this.value++;
    }
}
