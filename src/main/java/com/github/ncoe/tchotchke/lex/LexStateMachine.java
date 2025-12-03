/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.lex;

import com.github.ncoe.tchotchke.function.CharacterPredicate;
import com.github.ncoe.tchotchke.mutable.MutableInt;
import com.github.ncoe.tchotchke.util.Assertion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Lex State Machine
 *
 * @param <T> the token type
 */
public final class LexStateMachine<T> {
    private record Entry(CharacterPredicate pattern, LexAction action, int next) {
        //empty
    }

    private final StringBuilder builder = new StringBuilder();
    private final Map<Integer, List<Entry>> stateMap;
    private final Map<Integer, String> nameMap;
    private final TokenFactory<T> factory;

    private int state = 0;

    private LexStateMachine(Map<Integer, List<Entry>> stateMap, Map<Integer, String> nameMap, TokenFactory<T> factory) {
        this.stateMap = stateMap;
        this.nameMap = nameMap;
        this.factory = factory;
    }

    /**
     * Reset the machine.
     */
    public void reset() {
        builder.setLength(0);
        state = 0;
    }

    /**
     * Consume the token text.
     *
     * @return the text
     */
    public String consume() {
        String text = builder.toString();
        builder.setLength(0);
        return text;
    }

    /**
     * Conditionally call the consumer
     *
     * @param consumer the consumer
     */
    void consume(Consumer<T> consumer) {
        if (builder.isEmpty()) {
            return;
        }

        String name = nameMap.get(state);
        String text = builder.toString();

        T token = factory.invoke(name, text);
        consumer.accept(token);

        reset();
    }

    /**
     * Make a copy of the machine.
     *
     * @return a new state machine
     */
    public LexStateMachine<T> duplicate() {
        return new LexStateMachine<>(stateMap, nameMap, factory);
    }

    /**
     * Process the next character.
     *
     * @param ch         the character
     * @param downstream the downstream
     * @return true if more is acceptable
     */
    public boolean process(char ch, Predicate<T> downstream) {
        List<Entry> entryList = stateMap.get(state);
        Assertion.notNull(entryList, "Unexpected state: %d", state);

        int prev = state;
        for (Entry entry : entryList) {
            if (entry.pattern.test(ch)) {
                state = entry.next;

                return switch (entry.action) {
                    case REDUCE -> {
                        String name = nameMap.get(prev);
                        Assertion.isFalse(builder.isEmpty(), "empty text reduction in %s", name);

                        String text = consume();
                        T token = factory.invoke(name, text);

                        if (downstream.test(token)) {
                            yield process(ch, downstream);
                        }
                        yield false;
                    }
                    case SHIFT -> {
                        builder.append(ch);
                        yield true;
                    }
                    case SHIFT_REDUCE -> {
                        builder.append(ch);

                        String name = nameMap.get(prev);
                        String text = consume();

                        T token = factory.invoke(name, text);
                        yield downstream.test(token);
                    }
                };
            }
        }

        String name = nameMap.get(state);
        throw new LexException("Unhandled character %c in state %s", ch, name);
    }

    /**
     * Start building a state machine.
     *
     * @param init the initial state name
     * @return the builder
     */
    public static Builder<String> builder(String init) {
        return builder(init, (_, text) -> text);
    }

    /**
     * Start building a state machine.
     *
     * @param init    the initial state name
     * @param factory the token factory
     * @param <T>     the type of token
     * @return the builder
     */
    public static <T> Builder<T> builder(String init, TokenFactory<T> factory) {
        Assertion.notNull(init, "expected an initial state");
        Assertion.notNull(factory, "expected a token factory");
        return new Builder<>(init, factory);
    }

    /**
     * Lex State Machine Builder
     *
     * @param <T> the type of token
     */
    public static final class Builder<T> {
        private record Element(CharacterPredicate pattern, LexAction action, String next) {
            //empty
        }

        private final Map<String, List<Element>> buildStateMap = new LinkedHashMap<>();
        private final TokenFactory<T> factory;
        private final String init;

        private Builder(String init, TokenFactory<T> factory) {
            this.factory = factory;
            this.init = init;
        }

        /**
         * Add another state and/or transition to the machine.
         *
         * @param state   the state
         * @param pattern the pattern
         * @param action  the action
         * @return this
         */
        public Builder<T> add(String state, CharacterPredicate pattern, LexAction action) {
            return add(state, pattern, action, state);
        }

        /**
         * Add another state and/or transitions to the machine.
         *
         * @param state    the state
         * @param patterns the patterns
         * @param action   the action
         * @return this
         */
        public Builder<T> add(String state, Iterable<CharacterPredicate> patterns, LexAction action) {
            return add(state, patterns, action, state);
        }

        /**
         * Add another state and/or transitions to the machine.
         *
         * @param state    the state
         * @param patterns the patterns
         * @param action   the action
         * @param next     the next state
         * @return this
         */
        public Builder<T> add(String state, Iterable<CharacterPredicate> patterns, LexAction action, String next) {
            for (CharacterPredicate pattern : patterns) {
                add(state, pattern, action, next);
            }
            return this;
        }

        /**
         * Add another state and/or transition to the machine.
         *
         * @param state   the state
         * @param pattern the pattern
         * @param action  the action
         * @param next    the next state
         * @return this
         */
        public Builder<T> add(String state, CharacterPredicate pattern, LexAction action, String next) {
            List<Element> elementList = buildStateMap.computeIfAbsent(state, _ -> new ArrayList<>());
            elementList.add(new Element(pattern, action, next));
            return this;
        }

        /**
         * Build the lex state machine.
         *
         * @return the state machine
         */
        public LexStateMachine<T> build() {
            MutableInt state = new MutableInt();

            Map<String, Integer> nameMap = new HashMap<>();
            nameMap.put(init, state.intValue());

            for (String name : buildStateMap.keySet()) {
                nameMap.computeIfAbsent(name, _ -> state.incrementAndGet());
            }

            Map<Integer, List<Entry>> entryMap = new HashMap<>();
            Map<Integer, String> revNameMap = new HashMap<>();
            for (Map.Entry<String, List<Element>> entry : buildStateMap.entrySet()) {
                String name = entry.getKey();
                Integer number = nameMap.get(name);
                revNameMap.put(number, name);

                List<Entry> entryList = new ArrayList<>();
                for (Element element : entry.getValue()) {
                    Integer next = nameMap.get(element.next);
                    Assertion.notNull(next, element.next);
                    entryList.add(new Entry(element.pattern, element.action, next));
                }
                entryMap.put(number, entryList);
            }
            return new LexStateMachine<>(entryMap, revNameMap, factory);
        }
    }
}
