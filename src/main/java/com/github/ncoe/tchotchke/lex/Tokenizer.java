/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.lex;

import com.github.ncoe.tchotchke.stream.LineEndingGatherer;

import java.util.stream.Stream;

/**
 * State based tokenizer
 *
 * @param <S> the type of state
 * @param <T> the type of token
 */
@SuppressWarnings("ClassCanBeRecord")
public final class Tokenizer<S, T> {
    private final LexStateMachine<S, T> stateMachine;

    /**
     * Constructor
     *
     * @param stateMachine the state machine
     */
    public Tokenizer(LexStateMachine<S, T> stateMachine) {
        this.stateMachine = stateMachine;
    }

    /**
     * Split a string into tokens.
     *
     * @param text the text to be tokenized
     * @return the stream of tokens
     */
    public Stream<T> lex(String text) {
        //todo should int-stream get a gatherer specialization, modify these classes
        return text
            .codePoints()
            .mapToObj(i -> (char) i)
            .gather(new LineEndingGatherer())
            .gather(new TokenGatherer<>(stateMachine));
    }
}
