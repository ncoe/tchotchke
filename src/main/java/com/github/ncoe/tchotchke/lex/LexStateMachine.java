/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.lex;

import com.github.ncoe.tchotchke.function.CharacterPredicate;
import com.github.ncoe.tchotchke.option.Option;
import com.github.ncoe.tchotchke.util.Assertion;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Lex State Machine
 *
 * @param <S> the type of state
 * @param <T> the token type
 */
public final class LexStateMachine<S, T> {
    private record Entry<S>(CharacterPredicate predicate, LexAction action, S next) {
        //empty
    }

    private final StringBuilder builder = new StringBuilder();
    private final Map<S, List<Entry<S>>> stateMap;
    private final TokenFactory<S, T> factory;
    private final S init;

    private S state;

    private LexStateMachine(Map<S, List<Entry<S>>> stateMap, TokenFactory<S, T> factory, S init) {
        this.stateMap = stateMap;
        this.factory = factory;

        this.init = init;
        this.state = init;
    }

    /**
     * Make a copy of the machine.
     *
     * @return a new state machine
     */
    public LexStateMachine<S, T> duplicate() {
        return new LexStateMachine<>(stateMap, factory, init);
    }

    /**
     * Consume the token text.
     *
     * @return the text
     */
    String consume() {
        String text = builder.toString();
        builder.setLength(0);
        return text;
    }

    /**
     * Conditionally call the consumer
     *
     * @param consumer the consumer
     */
    void consume(Predicate<T> consumer) {
        String text = builder.toString();
        Option<T> tokenOpt = factory.invoke(state, text, true);
        consume(consumer, tokenOpt);
    }

    private boolean consume(Predicate<T> downstream, Option<T> tokenOpt) {
        return switch (tokenOpt) {
            case Option.None<T> _ -> true;
            case Option.Some<T> some -> downstream.test(some.value());
            case null -> throw new LexException("The token factory must never return null");
        };
    }

    /**
     * Process the next character.
     *
     * @param downstream the downstream
     * @param ch         the character
     * @return true if more tokens can be accepted
     */
    public boolean process(Predicate<T> downstream, char ch) {
        return process(downstream, ch, 0);
    }

    private boolean process(Predicate<T> downstream, char ch, int depth) {
        List<Entry<S>> entryList = stateMap.get(state);
        Assertion.notNull(entryList, "Unexpected state: %d", state);
        Assertion.isLess(depth, 3, "Bailing from potential stack overflow in state %s", state);

        S prev = state;
        for (Entry<S> entry : entryList) {
            if (entry.predicate.test(ch)) {
                state = entry.next;

                return switch (entry.action) {
                    case DEFER -> process(downstream, ch, depth + 1);
                    case REDUCE -> {
                        String text = consume();
                        Option<T> tokenOpt = factory.invoke(prev, text, false);
                        if (consume(downstream, tokenOpt)) {
                            yield process(downstream, ch, depth + 1);
                        }
                        yield false;
                    }
                    case SHIFT -> {
                        builder.append(ch);
                        yield true;
                    }
                    case SHIFT_REDUCE -> {
                        builder.append(ch);

                        String text = consume();
                        Option<T> tokenOpt = factory.invoke(prev, text, false);
                        yield consume(downstream, tokenOpt);
                    }
                    case SKIP -> true;
                    case SKIP_REDUCE -> {
                        String text = consume();
                        Option<T> tokenOpt = factory.invoke(prev, text, false);
                        yield consume(downstream, tokenOpt);
                    }
                };
            }
        }

        throw new LexException("Unhandled character %c in state %s", ch, state);
    }

    /**
     * Start building a state machine.
     *
     * @param init the initial state name
     * @return the builder
     */
    public static <S> Builder<S, String> builder(S init) {
        return builder(init, (_, text, end) -> {
            if (end && text.isEmpty()) {
                return Option.none();
            }
            return Option.some(text);
        });
    }

    /**
     * Start building a state machine.
     *
     * @param init    the initial state name
     * @param factory the token factory
     * @param <S>     the type of state
     * @param <T>     the type of token
     * @return the builder
     */
    public static <S, T> Builder<S, T> builder(S init, TokenFactory<S, T> factory) {
        Assertion.notNull(init, "expected an initial state");
        Assertion.notNull(factory, "expected a token factory");
        return new Builder<>(init, factory);
    }

    /**
     * Lex State Machine Builder
     *
     * @param <S> the type of state
     * @param <T> the type of token
     */
    public static final class Builder<S, T> {
        private final Map<S, List<Entry<S>>> stateMap = new LinkedHashMap<>();
        private final TokenFactory<S, T> factory;
        private final S init;

        private S current;

        private Builder(S init, TokenFactory<S, T> factory) {
            this.factory = factory;
            this.init = init;
            this.current = init;
        }

        /**
         * Set the current state under construction
         *
         * @param state the state
         * @return this
         */
        public Builder<S, T> begin(S state) {
            this.current = state;
            return this;
        }

        /**
         * Add another state and/or transitions to the machine.
         *
         * @param state      the state
         * @param predicates the predicates
         * @param action     the action
         * @return this
         */
        public Builder<S, T> add(S state, Iterable<CharacterPredicate> predicates, LexAction action) {
            return add(state, predicates, action, state);
        }

        /**
         * Add another state and/or transitions to the machine.
         *
         * @param predicates the predicates
         * @param action     the action
         * @return this
         */
        public Builder<S, T> add(Iterable<CharacterPredicate> predicates, LexAction action) {
            return add(predicates, action, current);
        }

        /**
         * Add another state and/or transitions to the machine.
         *
         * @param state      the state
         * @param predicates the predicates
         * @param action     the action
         * @param next       the next state
         * @return this
         */
        public Builder<S, T> add(S state, Iterable<CharacterPredicate> predicates, LexAction action, S next) {
            for (CharacterPredicate predicate : predicates) {
                add(state, predicate, action, next);
            }
            return this;
        }

        /**
         * Add another state and/or transitions to the machine.
         *
         * @param predicates the predicates
         * @param action     the action
         * @param next       the next state
         * @return this
         */
        public Builder<S, T> add(Iterable<CharacterPredicate> predicates, LexAction action, S next) {
            for (CharacterPredicate predicate : predicates) {
                add(predicate, action, next);
            }
            return this;
        }

        /**
         * Add another state and/or transition to the machine.
         *
         * @param state     the state
         * @param predicate the predicate
         * @param action    the action
         * @return this
         */
        public Builder<S, T> add(S state, CharacterPredicate predicate, LexAction action) {
            return add(state, predicate, action, state);
        }

        /**
         * Add another transition to the machine.
         *
         * @param predicate the predicate
         * @param action    the action
         * @return this
         */
        public Builder<S, T> add(CharacterPredicate predicate, LexAction action) {
            return add(predicate, action, current);
        }

        /**
         * Add another transition to the machine.
         *
         * @param predicate the predicate
         * @param action    the action
         * @param next      the next state
         * @return this
         */
        public Builder<S, T> add(CharacterPredicate predicate, LexAction action, S next) {
            return add(current, predicate, action, next);
        }

        /**
         * Add another state and/or transition to the machine.
         *
         * @param state     the state
         * @param predicate the predicate
         * @param action    the action
         * @param next      the next state
         * @return this
         */
        public Builder<S, T> add(S state, CharacterPredicate predicate, LexAction action, S next) {
            Assertion.notNull(state, "state cannot be null");
            Assertion.notNull(predicate, "predicate cannot be null");
            Assertion.notNull(action, "action cannot be null");
            Assertion.notNull(next, "next cannot be null");

            List<Entry<S>> elementList = stateMap.computeIfAbsent(state, _ -> new ArrayList<>());
            elementList.add(new Entry<>(predicate, action, next));

            this.current = state;
            return this;
        }

        /**
         * Build the lex state machine.
         *
         * @return the state machine
         */
        public LexStateMachine<S, T> build() {
            return build(false);
        }

        /**
         * Build the lex state machine.
         *
         * @param check check the token factory with all states
         * @return the state machine
         */
        public LexStateMachine<S, T> build(boolean check) {
            if (!stateMap.containsKey(init)) {
                throw new LexException("No transitions defined for state %s", init);
            }

            for (List<Entry<S>> entryList : stateMap.values()) {
                for (Entry<S> entry : entryList) {
                    if (!stateMap.containsKey(entry.next)) {
                        throw new LexException("No transitions defined for state %s", entry.next);
                    }
                }
            }

            if (check) {
                for (S state : stateMap.keySet()) {
                    factory.invoke(state, "", false);
                }
            }
            return new LexStateMachine<>(stateMap, factory, init);
        }
    }
}
