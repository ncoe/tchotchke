/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.option;

import com.github.ncoe.tchotchke.function.ShortConsumer;
import com.github.ncoe.tchotchke.function.ShortSupplier;

import java.util.NoSuchElementException;
import java.util.function.Supplier;
import java.util.stream.IntStream;

/**
 * A tagged union for a short
 */
public sealed interface OptionShort {
    /**
     * If a value is present, performs the given action with the value, otherwise does nothing.
     *
     * @param action the action to be performed, if a value is present
     * @throws NullPointerException if value is present and the given action is {@code null}
     */
    void ifPresent(ShortConsumer action);

    /**
     * If a value is present, performs the given action with the value, otherwise performs the given empty-based action.
     *
     * @param action      the action to be performed, if a value is present
     * @param emptyAction the empty-based action to be performed, if no value is present
     * @throws NullPointerException if a value is present and the given action is {@code null}, or no value is present and the given empty-based action is {@code null}.
     */
    void ifPresentOrElse(ShortConsumer action, Runnable emptyAction);

    /**
     * If a value is present, returns a sequential {@link IntStream} containing only that value, otherwise returns an empty {@code IntStream}.
     *
     * @return the optional value as an {@code IntStream}
     */
    IntStream stream();

    /**
     * If a value is present, returns the value, otherwise returns {@code other}.
     *
     * @param other the value to be returned, if no value is present.
     * @return the value, if present, otherwise {@code other}
     */
    short orElse(short other);

    /**
     * If a value is present, returns the value, otherwise returns the result produced by the supplying function.
     *
     * @param supplier the supplying function that produces a value to be returned
     * @return the value, if present, otherwise the result produced by the supplying function
     * @throws NullPointerException if no value is present and the supplying function is {@code null}
     */
    short orElseGet(ShortSupplier supplier);

    /**
     * If a value is present, returns the value, otherwise throws {@code NoSuchElementException}.
     *
     * @return the value, if present
     * @throws NoSuchElementException if no value is present
     */
    short orElseThrow();

    /**
     * If a value is present, returns the value, otherwise throws an exception produced by the exception supplying function.
     *
     * @param <X>               Type of the exception to be thrown
     * @param exceptionSupplier the supplying function that produces an exception to be thrown
     * @return the value, if present
     * @throws X                    if no value is present
     * @throws NullPointerException if no value is present and the exception supplying function is {@code null}
     */
    <X extends Throwable> short orElseThrow(Supplier<? extends X> exceptionSupplier) throws X;

    /**
     * An option with a value
     *
     * @param value the value
     */
    record Some(short value) implements OptionShort {
        @Override
        public void ifPresent(ShortConsumer action) {
            action.accept(value());
        }

        @Override
        public void ifPresentOrElse(ShortConsumer action, Runnable emptyAction) {
            action.accept(value());
        }

        @Override
        public IntStream stream() {
            return IntStream.of(value());
        }

        @Override
        public short orElse(short other) {
            return value();
        }

        @Override
        public short orElseGet(ShortSupplier supplier) {
            return value();
        }

        @Override
        public short orElseThrow() {
            return value();
        }

        @Override
        public <X extends Throwable> short orElseThrow(Supplier<? extends X> exceptionSupplier) {
            return value();
        }
    }

    /**
     * An option with a value
     *
     * @param value the value
     * @return the option
     */
    static OptionShort some(short value) {
        return new Some(value);
    }

    /**
     * An empty option
     */
    final class None implements OptionShort {
        static final OptionShort INSTANCE = new None();

        private None() {
            //empty
        }

        @Override
        public void ifPresent(ShortConsumer action) {
            //empty
        }

        @Override
        public void ifPresentOrElse(ShortConsumer action, Runnable emptyAction) {
            emptyAction.run();
        }

        @Override
        public IntStream stream() {
            return IntStream.empty();
        }

        @Override
        public short orElse(short other) {
            return other;
        }

        @Override
        public short orElseGet(ShortSupplier supplier) {
            return supplier.getAsShort();
        }

        @Override
        public short orElseThrow() {
            throw new NoSuchElementException();
        }

        @Override
        public <X extends Throwable> short orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
            throw exceptionSupplier.get();
        }
    }

    /**
     * An empty option
     *
     * @return the option
     */
    static OptionShort none() {
        return None.INSTANCE;
    }
}
