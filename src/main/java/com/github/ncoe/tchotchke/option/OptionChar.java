/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.option;

import com.github.ncoe.tchotchke.function.CharacterConsumer;
import com.github.ncoe.tchotchke.function.CharacterSupplier;

import java.util.NoSuchElementException;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * A tagged union for a character
 */
public sealed interface OptionChar {
    /**
     * If a value is present, performs the given action with the value, otherwise does nothing.
     *
     * @param action the action to be performed, if a value is present
     * @throws NullPointerException if value is present and the given action is {@code null}
     */
    void ifPresent(CharacterConsumer action);

    /**
     * If a value is present, performs the given action with the value, otherwise performs the given empty-based action.
     *
     * @param action      the action to be performed, if a value is present
     * @param emptyAction the empty-based action to be performed, if no value is present
     * @throws NullPointerException if a value is present and the given action is {@code null}, or no value is present and the given empty-based action is {@code null}.
     */
    void ifPresentOrElse(CharacterConsumer action, Runnable emptyAction);

    /**
     * If a value is present, returns a sequential {@link Stream} containing only that value, otherwise returns an empty {@code Stream}.
     *
     * @return the optional value as a {@code Stream}
     */
    Stream<Character> stream();

    /**
     * If a value is present, returns the value, otherwise returns {@code other}.
     *
     * @param other the value to be returned, if no value is present.
     * @return the value, if present, otherwise {@code other}
     */
    char orElse(char other);

    /**
     * If a value is present, returns the value, otherwise returns the result produced by the supplying function.
     *
     * @param supplier the supplying function that produces a value to be returned
     * @return the value, if present, otherwise the result produced by the supplying function
     * @throws NullPointerException if no value is present and the supplying function is {@code null}
     */
    char orElseGet(CharacterSupplier supplier);

    /**
     * If a value is present, returns the value, otherwise throws {@code NoSuchElementException}.
     *
     * @return the value, if present
     * @throws NoSuchElementException if no value is present
     */
    char orElseThrow();

    /**
     * If a value is present, returns the value, otherwise throws an exception produced by the exception supplying function.
     *
     * @param <X>               Type of the exception to be thrown
     * @param exceptionSupplier the supplying function that produces an exception to be thrown
     * @return the value, if present
     * @throws X                    if no value is present
     * @throws NullPointerException if no value is present and the exception supplying function is {@code null}
     */
    <X extends Throwable> char orElseThrow(Supplier<? extends X> exceptionSupplier) throws X;

    /**
     * An option with a value
     *
     * @param value the value
     */
    record Some(char value) implements OptionChar {
        @Override
        public void ifPresent(CharacterConsumer action) {
            action.accept(value());
        }

        @Override
        public void ifPresentOrElse(CharacterConsumer action, Runnable emptyAction) {
            action.accept(value());
        }

        @Override
        public Stream<Character> stream() {
            return Stream.of(value());
        }

        @Override
        public char orElse(char other) {
            return value();
        }

        @Override
        public char orElseGet(CharacterSupplier supplier) {
            return value();
        }

        @Override
        public char orElseThrow() {
            return value();
        }

        @Override
        public <X extends Throwable> char orElseThrow(Supplier<? extends X> exceptionSupplier) {
            return value();
        }
    }

    /**
     * An option with a value
     *
     * @param value the value
     * @return the option
     */
    static OptionChar some(char value) {
        return new Some(value);
    }

    /**
     * An empty option
     */
    final class None implements OptionChar {
        static final OptionChar INSTANCE = new None();

        private None() {
            //empty
        }

        @Override
        public void ifPresent(CharacterConsumer action) {
            //empty
        }

        @Override
        public void ifPresentOrElse(CharacterConsumer action, Runnable emptyAction) {
            emptyAction.run();
        }

        @Override
        public Stream<Character> stream() {
            return Stream.empty();
        }

        @Override
        public char orElse(char other) {
            return other;
        }

        @Override
        public char orElseGet(CharacterSupplier supplier) {
            return supplier.getAsChar();
        }

        @Override
        public char orElseThrow() {
            throw new NoSuchElementException();
        }

        @Override
        public <X extends Throwable> char orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
            throw exceptionSupplier.get();
        }
    }

    /**
     * An empty option
     *
     * @return the option
     */
    static OptionChar none() {
        return None.INSTANCE;
    }
}
