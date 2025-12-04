/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.lex;

import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Gatherer;

/**
 * Token Gatherer
 *
 * @param <T> the type of token
 */
@SuppressWarnings("ClassCanBeRecord")
final class TokenGatherer<T> implements Gatherer<Character, LexStateMachine<T>, T> {
    private final LexStateMachine<T> stateMachine;

    TokenGatherer(LexStateMachine<T> stateMachine) {
        this.stateMachine = stateMachine;
    }

    @Override
    public Supplier<LexStateMachine<T>> initializer() {
        return stateMachine::duplicate;
    }

    @Override
    public Integrator<LexStateMachine<T>, Character, T> integrator() {
        return (state, ch, downstream) -> state.process(downstream::push, ch);
    }

    @Override
    public BiConsumer<LexStateMachine<T>, Downstream<? super T>> finisher() {
        return (state, downstream) -> state.consume(downstream::push);
    }
}
