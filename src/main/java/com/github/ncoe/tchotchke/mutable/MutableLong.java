/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.mutable;

import com.github.ncoe.tchotchke.property.PropertyLong;

import java.util.function.LongBinaryOperator;

/**
 * A mutable long
 */
public final class MutableLong extends Number implements PropertyLong, Comparable<MutableLong> {
    private long value;

    /**
     * Constructor
     */
    public MutableLong() {
        this(0);
    }

    /**
     * Constructor
     *
     * @param value the initial value
     */
    public MutableLong(long value) {
        this.value = value;
    }

    @Override
    public int intValue() {
        return Math.toIntExact(this.value);
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
    public long getAsLong() {
        return this.value;
    }

    @Override
    public void accept(long value) {
        this.value = value;
    }

    @Override
    public int compareTo(MutableLong lhs) {
        return Long.compare(this.value, lhs.longValue());
    }

    @Override
    public int hashCode() {
        return Long.hashCode(this.value);
    }

    @Override
    public boolean equals(Object obj) {
        return switch (obj) {
            case Long i -> this.value == i;
            case MutableLong m -> this.value == m.longValue();
            case Number n -> this.value == n.longValue();
            case null, default -> false;
        };
    }

    @Override
    public String toString() {
        return Long.toString(this.value);
    }

    /**
     * Updates the current value, and returns it.
     *
     * @param op  the operator
     * @param lhs the left-hand side
     * @return the updated value
     */
    public long accumulateAndGet(LongBinaryOperator op, long lhs) {
        this.value = op.applyAsLong(this.value, lhs);
        return this.value;
    }

    /**
     * Updates the current value, and returns it.
     *
     * @param lhs the left-hand side
     * @return the updated value
     */
    public long addAndGet(long lhs) {
        this.value += lhs;
        return this.value;
    }

    /**
     * Decrements the current value, and returns it.
     *
     * @return the updated value
     */
    public long decrementAndGet() {
        return --this.value;
    }

    /**
     * Increments the current value, and returns it.
     *
     * @return the updated value
     */
    public long incrementAndGet() {
        return ++this.value;
    }

    /**
     * Updates the current value, returns the old value.
     *
     * @param op  the operator
     * @param lhs the left=-hand side
     * @return the original value
     */
    public long getAndAccumulate(LongBinaryOperator op, long lhs) {
        long oldValue = this.value;
        this.value = op.applyAsLong(this.value, lhs);
        return oldValue;
    }

    /**
     * Updates the current value, returns the old value.
     *
     * @param lhs the left-hand side
     * @return the original value
     */
    public long getAndAdd(long lhs) {
        long oldValue = this.value;
        this.value += lhs;
        return oldValue;
    }

    /**
     * Updates the current value, returns the old value.
     *
     * @return the original value
     */
    public long getAndDecrement() {
        return this.value--;
    }

    /**
     * Updates the current value, returns the old value.
     *
     * @return the original value
     */
    public long getAndIncrement() {
        return this.value++;
    }
}
