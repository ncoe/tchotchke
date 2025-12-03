/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.option;

import com.github.ncoe.tchotchke.util.Assertion;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * A tagged union for a value
 *
 * @param <T> the type of value
 */
public sealed interface Option<T> {
    /**
     * If a value is present, performs the given action with the value, otherwise does nothing.
     *
     * @param action the action to be performed, if a value is present
     * @throws NullPointerException if value is present and the given action is {@code null}
     */
    void ifPresent(Consumer<? super T> action);

    /**
     * If a value is present, performs the given action with the value, otherwise performs the given empty-based action.
     *
     * @param action      the action to be performed, if a value is present
     * @param emptyAction the empty-based action to be performed, if no value is present
     * @throws NullPointerException if a value is present and the given action is {@code null}, or no value is present and the given empty-based action is {@code null}.
     */
    void ifPresentOrElse(Consumer<? super T> action, Runnable emptyAction);

    /**
     * If a value is present, returns a sequential {@link Stream} containing only that value, otherwise returns an empty {@code Stream}.
     *
     * @return the optional value as a {@code Stream}
     */
    Stream<T> stream();

    /**
     * If a value is present, returns the value, otherwise returns {@code other}.
     *
     * @param other the value to be returned, if no value is present. May be {@code null}.
     * @return the value, if present, otherwise {@code other}
     */
    T orElse(T other);

    /**
     * If a value is present, returns the value, otherwise returns the result produced by the supplying function.
     *
     * @param supplier the supplying function that produces a value to be returned
     * @return the value, if present, otherwise the result produced by the supplying function
     * @throws NullPointerException if no value is present and the supplying function is {@code null}
     */
    T orElseGet(Supplier<? extends T> supplier);

    /**
     * If a value is present, returns the value, otherwise throws {@code NoSuchElementException}.
     *
     * @return the non-{@code null} value described by this {@code Option}
     * @throws NoSuchElementException if no value is present
     */
    T orElseThrow();

    /**
     * If a value is present, returns the value, otherwise throws an exception produced by the exception supplying function.
     *
     * @param <X>               Type of the exception to be thrown
     * @param exceptionSupplier the supplying function that produces an exception to be thrown
     * @return the value, if present
     * @throws X                    if no value is present
     * @throws NullPointerException if no value is present and the exception supplying function is {@code null} or produces a {@code null} result
     */
    <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X;

    /**
     * An option with a value
     *
     * @param value the value
     * @param <T>   the type of value
     */
    record Some<T>(T value) implements Option<T> {
        @Override
        public void ifPresent(Consumer<? super T> action) {
            action.accept(value());
        }

        @Override
        public void ifPresentOrElse(Consumer<? super T> action, Runnable emptyAction) {
            action.accept(value());
        }

        @Override
        public Stream<T> stream() {
            return Stream.of(value());
        }

        @Override
        public T orElse(T other) {
            return value();
        }

        @Override
        public T orElseGet(Supplier<? extends T> supplier) {
            return value();
        }

        @Override
        public T orElseThrow() {
            return value();
        }

        @Override
        public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) {
            return value();
        }
    }

    /**
     * An option with a value
     *
     * @param value the value
     * @param <T>   the type of value
     * @return the option
     */
    static <T> Option<T> some(T value) {
        Assertion.notNull(value, "value is required");
        return new Some<>(value);
    }

    /**
     * An empty option
     *
     * @param <T> the type of none
     */
    final class None<T> implements Option<T> {
        static final Option<?> INSTANCE = new None<>();

        private None() {
            //empty
        }

        @Override
        public void ifPresent(Consumer<? super T> action) {
            //empty
        }

        @Override
        public void ifPresentOrElse(Consumer<? super T> action, Runnable emptyAction) {
            emptyAction.run();
        }

        @Override
        public Stream<T> stream() {
            return Stream.empty();
        }

        @Override
        public T orElse(T other) {
            return other;
        }

        @Override
        public T orElseGet(Supplier<? extends T> supplier) {
            return supplier.get();
        }

        @Override
        public T orElseThrow() {
            throw new NoSuchElementException();
        }

        @Override
        public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
            throw exceptionSupplier.get();
        }
    }

    /**
     * An empty option
     *
     * @param <T> the type of value
     * @return the option
     */
    @SuppressWarnings("unchecked")
    static <T> Option<T> none() {
        return (Option<T>) None.INSTANCE;
    }
}
