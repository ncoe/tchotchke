/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.mutable;

import com.github.ncoe.tchotchke.property.PropertyInt;

import java.util.function.IntBinaryOperator;

/**
 * A mutable integer
 */
public final class MutableInt extends Number implements PropertyInt, Comparable<MutableInt> {
    private int value;

    /**
     * Constructor
     */
    public MutableInt() {
        this(0);
    }

    /**
     * Constructor
     *
     * @param value the initial value
     */
    public MutableInt(int value) {
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
    public int getAsInt() {
        return this.value;
    }

    @Override
    public void accept(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(MutableInt lhs) {
        return Integer.compare(this.value, lhs.intValue());
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(this.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Integer i) {
            return this.value == i;
        }
        if (obj instanceof MutableInt m) {
            return this.value == m.intValue();
        }
        return false;
    }

    @Override
    public String toString() {
        return Integer.toString(this.value);
    }

    /**
     * Updates the current value, and returns it.
     *
     * @param op  the operator
     * @param lhs the left-hand side
     * @return the updated value
     */
    public int accumulateAndGet(IntBinaryOperator op, int lhs) {
        this.value = op.applyAsInt(this.value, lhs);
        return this.value;
    }

    /**
     * Updates the current value, and returns it.
     *
     * @param lhs the left-hand side
     * @return the updated value
     */
    public int addAndGet(int lhs) {
        this.value += lhs;
        return this.value;
    }

    /**
     * Decrements the current value, and returns it.
     *
     * @return the updated value
     */
    public int decrementAndGet() {
        return --this.value;
    }

    /**
     * Increments the current value, and returns it.
     *
     * @return the updated value
     */
    public int incrementAndGet() {
        return ++this.value;
    }

    /**
     * Updates the current value, returns the old value.
     *
     * @param op  the operator
     * @param lhs the left=-hand side
     * @return the original value
     */
    public int getAndAccumulate(IntBinaryOperator op, int lhs) {
        int oldValue = this.value;
        this.value = op.applyAsInt(this.value, lhs);
        return oldValue;
    }

    /**
     * Updates the current value, returns the old value.
     *
     * @param lhs the left-hand side
     * @return the original value
     */
    public int getAndAdd(int lhs) {
        int oldValue = this.value;
        this.value += lhs;
        return oldValue;
    }

    /**
     * Updates the current value, returns the old value.
     *
     * @return the original value
     */
    public int getAndDecrement() {
        return this.value--;
    }

    /**
     * Updates the current value, returns the old value.
     *
     * @return the original value
     */
    public int getAndIncrement() {
        return this.value++;
    }
}
