/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.mutable;

import com.github.ncoe.tchotchke.function.ShortBinaryOperator;
import com.github.ncoe.tchotchke.property.PropertyShort;

/**
 * A mutable short
 */
public final class MutableShort extends Number implements PropertyShort, Comparable<MutableShort> {
    private short value;

    /**
     * Constructor
     */
    public MutableShort() {
        this((short) 0);
    }

    /**
     * Constructor
     *
     * @param value the initial value
     */
    public MutableShort(short value) {
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
    public short getAsShort() {
        return this.value;
    }

    @Override
    public void accept(short value) {
        this.value = value;
    }

    @Override
    public int compareTo(MutableShort lhs) {
        return Short.compare(this.value, lhs.getAsShort());
    }

    @Override
    public int hashCode() {
        return Short.hashCode(this.value);
    }

    @Override
    public boolean equals(Object obj) {
        return switch (obj) {
            case Short i -> this.value == i;
            case MutableShort m -> this.value == m.getAsShort();
            case Number n -> this.value == n.shortValue();
            case null, default -> false;
        };
    }

    @Override
    public String toString() {
        return Short.toString(this.value);
    }

    /**
     * Updates the current value, and returns it.
     *
     * @param op  the operator
     * @param lhs the left-hand side
     * @return the updated value
     */
    public short accumulateAndGet(ShortBinaryOperator op, short lhs) {
        this.value = op.applyAsShort(this.value, lhs);
        return this.value;
    }

    /**
     * Updates the current value, and returns it.
     *
     * @param lhs the left-hand side
     * @return the updated value
     */
    public short addAndGet(short lhs) {
        this.value += lhs;
        return this.value;
    }

    /**
     * Decrements the current value, and returns it.
     *
     * @return the updated value
     */
    public short decrementAndGet() {
        return --this.value;
    }

    /**
     * Increments the current value, and returns it.
     *
     * @return the updated value
     */
    public short incrementAndGet() {
        return ++this.value;
    }

    /**
     * Updates the current value, returns the old value.
     *
     * @param op  the operator
     * @param lhs the left=-hand side
     * @return the original value
     */
    public short getAndAccumulate(ShortBinaryOperator op, short lhs) {
        short oldValue = this.value;
        this.value = op.applyAsShort(this.value, lhs);
        return oldValue;
    }

    /**
     * Updates the current value, returns the old value.
     *
     * @param lhs the left-hand side
     * @return the original value
     */
    public short getAndAdd(short lhs) {
        short oldValue = this.value;
        this.value += lhs;
        return oldValue;
    }

    /**
     * Updates the current value, returns the old value.
     *
     * @return the original value
     */
    public short getAndDecrement() {
        return this.value--;
    }

    /**
     * Updates the current value, returns the old value.
     *
     * @return the original value
     */
    public short getAndIncrement() {
        return this.value++;
    }
}
