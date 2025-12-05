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
 * @param <S> the type of state
 * @param <T> the type of token
 */
@SuppressWarnings("ClassCanBeRecord")
final class TokenGatherer<S, T> implements Gatherer<Character, LexStateMachine<S, T>, T> {
    private final LexStateMachine<S, T> stateMachine;

    TokenGatherer(LexStateMachine<S, T> stateMachine) {
        this.stateMachine = stateMachine;
    }

    @Override
    public Supplier<LexStateMachine<S, T>> initializer() {
        return stateMachine::duplicate;
    }

    @Override
    public Integrator<LexStateMachine<S, T>, Character, T> integrator() {
        return (state, ch, downstream) -> state.process(downstream::push, ch);
    }

    @Override
    public BiConsumer<LexStateMachine<S, T>, Downstream<? super T>> finisher() {
        return (state, downstream) -> state.consume(downstream::push);
    }
}
