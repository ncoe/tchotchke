/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.option;

import java.util.NoSuchElementException;
import java.util.function.LongConsumer;
import java.util.function.LongSupplier;
import java.util.function.Supplier;
import java.util.stream.LongStream;

/**
 * A tagged union for a long
 */
public sealed interface OptionLong {
    /**
     * If a value is present, performs the given action with the value, otherwise does nothing.
     *
     * @param action the action to be performed, if a value is present
     * @throws NullPointerException if value is present and the given action is {@code null}
     */
    void ifPresent(LongConsumer action);

    /**
     * If a value is present, performs the given action with the value, otherwise performs the given empty-based action.
     *
     * @param action      the action to be performed, if a value is present
     * @param emptyAction the empty-based action to be performed, if no value is present
     * @throws NullPointerException if a value is present and the given action is {@code null}, or no value is present and the given empty-based action is {@code null}.
     */
    void ifPresentOrElse(LongConsumer action, Runnable emptyAction);

    /**
     * If a value is present, returns a sequential {@link LongStream} containing only that value, otherwise returns an empty {@code LongStream}.
     *
     * @return the optional value as an {@code LongStream}
     */
    LongStream stream();

    /**
     * If a value is present, returns the value, otherwise returns {@code other}.
     *
     * @param other the value to be returned, if no value is present
     * @return the value, if present, otherwise {@code other}
     */
    long orElse(long other);

    /**
     * If a value is present, returns the value, otherwise returns the result produced by the supplying function.
     *
     * @param supplier the supplying function that produces a value to be returned
     * @return the value, if present, otherwise the result produced by the supplying function
     * @throws NullPointerException if no value is present and the supplying function is {@code null}
     */
    long orElseGet(LongSupplier supplier);

    /**
     * If a value is present, returns the value, otherwise throws {@code NoSuchElementException}.
     *
     * @return the value, if present
     * @throws NoSuchElementException if no value is present
     */
    long orElseThrow();

    /**
     * If a value is present, returns the value, otherwise throws an exception produced by the exception supplying function.
     *
     * @param <X>               Type of the exception to be thrown
     * @param exceptionSupplier the supplying function that produces an exception to be thrown
     * @return the value, if present
     * @throws X                    if no value is present
     * @throws NullPointerException if no value is present and the exception supplying function is {@code null}
     */
    <X extends Throwable> long orElseThrow(Supplier<? extends X> exceptionSupplier) throws X;

    /**
     * An option with a value
     *
     * @param value the value
     */
    record Some(long value) implements OptionLong {
        @Override
        public void ifPresent(LongConsumer action) {
            action.accept(value());
        }

        @Override
        public void ifPresentOrElse(LongConsumer action, Runnable emptyAction) {
            action.accept(value());
        }

        @Override
        public LongStream stream() {
            return LongStream.of(value());
        }

        @Override
        public long orElse(long other) {
            return value();
        }

        @Override
        public long orElseGet(LongSupplier supplier) {
            return value();
        }

        @Override
        public long orElseThrow() {
            return value();
        }

        @Override
        public <X extends Throwable> long orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
            return value();
        }
    }

    /**
     * An option with a value
     *
     * @param value the value
     * @return the option
     */
    static OptionLong some(long value) {
        return new Some(value);
    }

    /**
     * An empty option
     */
    final class None implements OptionLong {
        static final OptionLong INSTANCE = new None();

        private None() {
            //empty
        }

        @Override
        public void ifPresent(LongConsumer action) {
            //empty
        }

        @Override
        public void ifPresentOrElse(LongConsumer action, Runnable emptyAction) {
            emptyAction.run();
        }

        @Override
        public LongStream stream() {
            return LongStream.empty();
        }

        @Override
        public long orElse(long other) {
            return other;
        }

        @Override
        public long orElseGet(LongSupplier supplier) {
            return supplier.getAsLong();
        }

        @Override
        public long orElseThrow() {
            throw new NoSuchElementException();
        }

        @Override
        public <X extends Throwable> long orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
            throw exceptionSupplier.get();
        }
    }

    /**
     * An empty option
     *
     * @return the option
     */
    static OptionLong none() {
        return None.INSTANCE;
    }
}
