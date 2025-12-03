/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.option;

import com.github.ncoe.tchotchke.function.BooleanConsumer;

import java.util.NoSuchElementException;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * A tagged union for a boolean
 */
public sealed interface OptionBool {
    /**
     * If a value is present, performs the given action with the value, otherwise does nothing.
     *
     * @param action the action to be performed, if a value is present
     * @throws NullPointerException if value is present and the given action is {@code null}
     */
    void ifPresent(BooleanConsumer action);

    /**
     * If a value is present, performs the given action with the value, otherwise performs the given empty-based action.
     *
     * @param action      the action to be performed, if a value is present
     * @param emptyAction the empty-based action to be performed, if no value is present
     * @throws NullPointerException if a value is present and the given action is {@code null}, or no value is present and the given empty-based action is {@code null}.
     */
    void ifPresentOrElse(BooleanConsumer action, Runnable emptyAction);

    /**
     * If a value is present, returns a sequential {@link Stream} containing only that value, otherwise returns an empty {@code Stream}.
     *
     * @return the optional value as a {@code Stream}
     */
    Stream<Boolean> stream();

    /**
     * If a value is present, returns the value, otherwise returns {@code other}.
     *
     * @param other the value to be returned, if no value is present.
     * @return the value, if present, otherwise {@code other}
     */
    boolean orElse(boolean other);

    /**
     * If a value is present, returns the value, otherwise returns the result produced by the supplying function.
     *
     * @param supplier the supplying function that produces a value to be returned
     * @return the value, if present, otherwise the result produced by the supplying function
     * @throws NullPointerException if no value is present and the supplying function is {@code null}
     */
    boolean orElseGet(BooleanSupplier supplier);

    /**
     * If a value is present, returns the value, otherwise throws {@code NoSuchElementException}.
     *
     * @return the value, if present
     * @throws NoSuchElementException if no value is present
     */
    boolean orElseThrow();

    /**
     * If a value is present, returns the value, otherwise throws an exception produced by the exception supplying function.
     *
     * @param <X>               Type of the exception to be thrown
     * @param exceptionSupplier the supplying function that produces an exception to be thrown
     * @return the value, if present
     * @throws X                    if no value is present
     * @throws NullPointerException if no value is present and the exception supplying function is {@code null}
     */
    <X extends Throwable> boolean orElseThrow(Supplier<? extends X> exceptionSupplier) throws X;

    /**
     * An option with a value
     *
     * @param value the value
     */
    record Some(boolean value) implements OptionBool {
        @Override
        public void ifPresent(BooleanConsumer action) {
            action.accept(value());
        }

        @Override
        public void ifPresentOrElse(BooleanConsumer action, Runnable emptyAction) {
            action.accept(value());
        }

        @Override
        public Stream<Boolean> stream() {
            return Stream.of(value());
        }

        @Override
        public boolean orElse(boolean other) {
            return value();
        }

        @Override
        public boolean orElseGet(BooleanSupplier supplier) {
            return value();
        }

        @Override
        public boolean orElseThrow() {
            return value();
        }

        @Override
        public <X extends Throwable> boolean orElseThrow(Supplier<? extends X> exceptionSupplier) {
            return value();
        }
    }

    /**
     * An option with a value
     *
     * @param value the value
     * @return the option
     */
    static OptionBool some(boolean value) {
        return new Some(value);
    }

    /**
     * An empty option
     */
    final class None implements OptionBool {
        static final OptionBool INSTANCE = new None();

        private None() {
            //empty
        }

        @Override
        public void ifPresent(BooleanConsumer action) {
            //empty
        }

        @Override
        public void ifPresentOrElse(BooleanConsumer action, Runnable emptyAction) {
            emptyAction.run();
        }

        @Override
        public Stream<Boolean> stream() {
            return Stream.empty();
        }

        @Override
        public boolean orElse(boolean other) {
            return other;
        }

        @Override
        public boolean orElseGet(BooleanSupplier supplier) {
            return supplier.getAsBoolean();
        }

        @Override
        public boolean orElseThrow() {
            throw new NoSuchElementException();
        }

        @Override
        public <X extends Throwable> boolean orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
            throw exceptionSupplier.get();
        }
    }

    /**
     * An empty option
     *
     * @return the option
     */
    static OptionBool none() {
        return None.INSTANCE;
    }
}
