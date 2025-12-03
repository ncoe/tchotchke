/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.stream;

import com.github.ncoe.tchotchke.lex.Characters;
import com.github.ncoe.tchotchke.mutable.MutableBool;

import java.util.function.Supplier;
import java.util.stream.Gatherer;

/**
 * Line Ending Gatherer
 */
public final class LineEndingGatherer implements Gatherer<Character, MutableBool, Character> {
    @Override
    public Supplier<MutableBool> initializer() {
        return MutableBool::new;
    }

    @Override
    public Integrator<MutableBool, Character, Character> integrator() {
        return (state, ch, downstream) -> {
            if (Characters.CR.test(ch)) {
                state.setTrue();
                return downstream.push((char) Characters.CP_LF);
            }
            if (Characters.LF.test(ch) && state.isTrue()) {
                state.setFalse();
                return true;
            }

            state.setFalse();
            return downstream.push(ch);
        };
    }
}
