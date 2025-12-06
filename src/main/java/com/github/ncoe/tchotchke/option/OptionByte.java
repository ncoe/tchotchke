/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.option;

import com.github.ncoe.tchotchke.function.ByteConsumer;
import com.github.ncoe.tchotchke.function.ByteSupplier;

import java.util.NoSuchElementException;
import java.util.function.Supplier;
import java.util.stream.IntStream;

/**
 * A tagged union for a byte
 */
public sealed interface OptionByte {
    /**
     * If a value is present, performs the given action with the value, otherwise does nothing.
     *
     * @param action the action to be performed, if a value is present
     * @throws NullPointerException if value is present and the given action is {@code null}
     */
    void ifPresent(ByteConsumer action);

    /**
     * If a value is present, performs the given action with the value, otherwise performs the given empty-based action.
     *
     * @param action      the action to be performed, if a value is present
     * @param emptyAction the empty-based action to be performed, if no value is present
     * @throws NullPointerException if a value is present and the given action is {@code null}, or no value is present and the given empty-based action is {@code null}.
     */
    void ifPresentOrElse(ByteConsumer action, Runnable emptyAction);

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
    byte orElse(byte other);

    /**
     * If a value is present, returns the value, otherwise returns the result produced by the supplying function.
     *
     * @param supplier the supplying function that produces a value to be returned
     * @return the value, if present, otherwise the result produced by the supplying function
     * @throws NullPointerException if no value is present and the supplying function is {@code null}
     */
    byte orElseGet(ByteSupplier supplier);

    /**
     * If a value is present, returns the value, otherwise throws {@code NoSuchElementException}.
     *
     * @return the value, if present
     * @throws NoSuchElementException if no value is present
     */
    byte orElseThrow();

    /**
     * If a value is present, returns the value, otherwise throws an exception produced by the exception supplying function.
     *
     * @param <X>               Type of the exception to be thrown
     * @param exceptionSupplier the supplying function that produces an exception to be thrown
     * @return the value, if present
     * @throws X                    if no value is present
     * @throws NullPointerException if no value is present and the exception supplying function is {@code null}
     */
    <X extends Throwable> byte orElseThrow(Supplier<? extends X> exceptionSupplier) throws X;

    /**
     * An option with a value
     *
     * @param value the value
     */
    record Some(byte value) implements OptionByte {
        @Override
        public void ifPresent(ByteConsumer action) {
            action.accept(value());
        }

        @Override
        public void ifPresentOrElse(ByteConsumer action, Runnable emptyAction) {
            action.accept(value());
        }

        @Override
        public IntStream stream() {
            return IntStream.of(value());
        }

        @Override
        public byte orElse(byte other) {
            return value();
        }

        @Override
        public byte orElseGet(ByteSupplier supplier) {
            return value();
        }

        @Override
        public byte orElseThrow() {
            return value();
        }

        @Override
        public <X extends Throwable> byte orElseThrow(Supplier<? extends X> exceptionSupplier) {
            return value();
        }
    }

    /**
     * An option with a value
     *
     * @param value the value
     * @return the option
     */
    static OptionByte some(byte value) {
        return new Some(value);
    }

    /**
     * An empty option
     */
    final class None implements OptionByte {
        static final OptionByte INSTANCE = new None();

        private None() {
            //empty
        }

        @Override
        public void ifPresent(ByteConsumer action) {
            //empty
        }

        @Override
        public void ifPresentOrElse(ByteConsumer action, Runnable emptyAction) {
            emptyAction.run();
        }

        @Override
        public IntStream stream() {
            return IntStream.empty();
        }

        @Override
        public byte orElse(byte other) {
            return other;
        }

        @Override
        public byte orElseGet(ByteSupplier supplier) {
            return supplier.getAsByte();
        }

        @Override
        public byte orElseThrow() {
            throw new NoSuchElementException();
        }

        @Override
        public <X extends Throwable> byte orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
            throw exceptionSupplier.get();
        }
    }

    /**
     * An empty option
     *
     * @return the option
     */
    static OptionByte none() {
        return None.INSTANCE;
    }
}
