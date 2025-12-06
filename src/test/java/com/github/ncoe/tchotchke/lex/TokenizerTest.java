/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.lex;

import com.github.ncoe.tchotchke.function.CharacterPredicate;
import com.github.ncoe.tchotchke.option.Option;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class TokenizerTest {
    private enum LexState {
        START,
        SPACE,
        IDENTIFIER,
        INTEGER,
        ERROR,
    }

    private static final Tokenizer<LexState, String> TOKENIZER = new Tokenizer<>(LexStateMachine
        .builder(LexState.START)
        //-------------------------------------------------------------------------------
        .add(List.of(Characters.HT, Characters.LF), LexAction.SHIFT, LexState.SPACE)
        .add(Characters.CONTROL, LexAction.SHIFT, LexState.ERROR)
        .add(Characters.SPACE, LexAction.SHIFT, LexState.SPACE)
        .add(Characters.DIGIT, LexAction.SHIFT, LexState.INTEGER)
        .add(List.of(Characters.UPPER, Characters.of('_'), Characters.LOWER), LexAction.SHIFT, LexState.IDENTIFIER)
        .add(Characters.DEL, LexAction.SHIFT, LexState.ERROR)
        .add(Characters.ASCII, LexAction.SHIFT_REDUCE)
        .add(Characters.any(), LexAction.SHIFT, LexState.ERROR)
        //-------------------------------------------------------------------------------
        .begin(LexState.SPACE)
        .add(List.of(Characters.HT, Characters.LF), LexAction.SHIFT)
        .add(Characters.SPACE, LexAction.SHIFT)
        .add(Characters.any(), LexAction.REDUCE, LexState.START)
        //-------------------------------------------------------------------------------
        .begin(LexState.IDENTIFIER)
        .add(Characters.DIGIT, LexAction.SHIFT)
        .add(List.of(Characters.UPPER, Characters.of('_'), Characters.LOWER), LexAction.SHIFT)
        .add(Characters.any(), LexAction.REDUCE, LexState.START)
        //-------------------------------------------------------------------------------
        .begin(LexState.INTEGER)
        .add(Characters.DIGIT, LexAction.SHIFT)
        .add(Characters.any(), LexAction.REDUCE, LexState.START)
        //-------------------------------------------------------------------------------
        .begin(LexState.ERROR)
        .add(List.of(Characters.HT, Characters.LF), LexAction.REDUCE, LexState.START)
        .add(Characters.CONTROL, LexAction.SHIFT)
        .add(Characters.ASCII, LexAction.REDUCE, LexState.START)
        .add(Characters.any(), LexAction.SHIFT)
        //-------------------------------------------------------------------------------
        .build(true));

    @DataProvider
    public static Object[][] lexProvider() {
        return new Object[][]{
            {"hello  12345", List.of("hello", "  ", "12345")},
            {"$@", List.of("$", "@")},
            {"a\r\nb", List.of("a", "\n", "b")},
            {"a\rb", List.of("a", "\n", "b")},
            {"a\nb", List.of("a", "\n", "b")},
        };
    }

    @Test(dataProvider = "lexProvider")
    public void lex(String source, List<String> expectedList) {
        List<String> tokenList = TOKENIZER.lex(source).toList();
        Assert.assertEquals(tokenList, expectedList);
    }

    @Test(expectedExceptions = LexException.class)
    public void error() {
        Tokenizer<String, String> tokenizer = new Tokenizer<>(LexStateMachine
            .builder("start")
            .add("start", Characters.DIGIT, LexAction.SHIFT)
            .build());
        long num = tokenizer.lex("oops").count();
        Assert.assertEquals(num, 0);
    }

    @Test
    public void limit() {
        Tokenizer<String, String> tokenizer = new Tokenizer<>(LexStateMachine
            .builder("start")
            .add(Characters.of('o'), LexAction.SHIFT, "o")
            .add(Characters.of('p'), LexAction.SHIFT_REDUCE)
            .add(Characters.any(), LexAction.SHIFT)
            .begin("o")
            .add(Characters.of('o'), LexAction.SHIFT)
            .add(Characters.any(), LexAction.REDUCE)
            .build());

        long num = tokenizer.lex("oops").limit(1).count();
        Assert.assertEquals(num, 1);

        long cnt = tokenizer.lex("ops").limit(1).count();
        Assert.assertEquals(cnt, 1);
    }

    @Test
    public void skip() {
        Tokenizer<String, String> tokenizer = new Tokenizer<>(LexStateMachine
            .builder("start")
            .add("start", Characters.QT, LexAction.SKIP, "string")
            .add("start", Characters.any(), LexAction.SHIFT, "error")
            .add("string", List.of(Characters.HT, Characters.VT), LexAction.SHIFT)
            .add("string", Characters.CONTROL, LexAction.SHIFT, "error")
            .add("string", Characters.QT, LexAction.SKIP_REDUCE, "start")
            .add("string", Characters.BACKSLASH, LexAction.SHIFT, "string-escape")
            .add("string", Characters.ASCII, LexAction.SHIFT)
            .add("string", Characters.any(), LexAction.SHIFT, "error")
            .add("string-escape", Characters.CONTROL, LexAction.SHIFT, "error")
            .add("string-escape", Characters.ASCII, LexAction.SHIFT, "string")
            .add("string-escape", Characters.any(), LexAction.SHIFT, "error")
            .add("error", Characters.any(), LexAction.SHIFT)
            .build());

        List<String> tokenList = tokenizer.lex("'hello world''oops'").toList();
        Assert.assertEquals(tokenList, List.of("hello world", "oops"));
    }

    @Test
    public void tokenOption() {
        CharacterPredicate digit = Characters.inclusive('0', '9');
        CharacterPredicate lower = Characters.inclusive('a', 'z');
        CharacterPredicate space = Characters.of(' ');
        CharacterPredicate upper = Characters.inclusive('A', 'Z');

        Tokenizer<String, String> tokenizer = new Tokenizer<>(LexStateMachine
            .builder("start", (state, text, _) -> {
                if (text.isEmpty() || "space".equals(state) || "error".equals(state)) {
                    return Option.none();
                }
                return Option.some(text);
            })
            .add(space, LexAction.SHIFT, "space")
            .add(digit, LexAction.SHIFT, "integer")
            .add(List.of(upper, lower), LexAction.SHIFT, "identifier")
            .add(Characters.any(), LexAction.SHIFT, "error")
            .begin("space")
            .add(space, LexAction.SHIFT)
            .add(Characters.any(), LexAction.REDUCE, "start")
            .begin("integer")
            .add(digit, LexAction.SHIFT)
            .add(Characters.any(), LexAction.REDUCE, "start")
            .begin("identifier")
            .add(List.of(upper, lower), LexAction.SHIFT)
            .add(Characters.any(), LexAction.REDUCE, "start")
            .begin("error")
            .add(space, LexAction.REDUCE, "start")
            .add(digit, LexAction.REDUCE, "start")
            .add(List.of(upper, lower), LexAction.REDUCE, "start")
            .add(Characters.any(), LexAction.SHIFT)
            .build());
        List<String> tokenList = tokenizer.lex("hello world 123").toList();
        Assert.assertEquals(tokenList, List.of("hello", "world", "123"));
    }

    @Test(expectedExceptions = LexException.class)
    public void noInitialState() {
        LexStateMachine.builder("start").build();
    }

    @Test(expectedExceptions = LexException.class)
    public void unknownState() {
        LexStateMachine
            .builder("start")
            .add("start", Characters.any(), LexAction.SHIFT, "oops")
            .build();
    }

    @Test(expectedExceptions = LexException.class)
    public void nullToken() {
        LexStateMachine<Integer, String> machine = LexStateMachine
            .builder(0, (TokenFactory<Integer, String>) (_, _, _) -> null)
            .add(Characters.any(), LexAction.SHIFT)
            .build();
        machine.process(null, 'a');
        machine.consume(_ -> true);
    }

    @Test
    public void defer() {
        LexStateMachine<Integer, String> machine = LexStateMachine
            .builder(0)
            .add(Characters.UPPER, LexAction.SHIFT, 1)
            .add(Characters.any(), LexAction.SHIFT_REDUCE)
            .begin(1)
            .add(Characters.LOWER, LexAction.DEFER, 0)
            .add(Characters.UPPER, LexAction.SHIFT)
            .add(Characters.any(), LexAction.REDUCE)
            .build();
        machine.process(null, 'A');
        machine.process(_ -> true, 'b');
    }
}
