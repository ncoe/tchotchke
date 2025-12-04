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
    private static final Tokenizer<String> TOKENIZER = new Tokenizer<>(LexStateMachine
        .builder("start")
        //-------------------------------------------------------------------------------
        .add("start", List.of(Characters.HT, Characters.LF), LexAction.SHIFT, "space")
        .add("start", Characters.CONTROL, LexAction.SHIFT, "error")
        .add("start", Characters.SPACE, LexAction.SHIFT, "space")
        .add("start", Characters.DIGIT, LexAction.SHIFT, "integer")
        .add("start", List.of(Characters.UPPER, CharacterPredicate.of('_'), Characters.LOWER), LexAction.SHIFT, "identifier")
        .add("start", Characters.DEL, LexAction.SHIFT, "error")
        .add("start", Characters.ASCII, LexAction.SHIFT_REDUCE)
        .add("start", CharacterPredicate.any(), LexAction.SHIFT, "error")
        //-------------------------------------------------------------------------------
        .add("space", List.of(Characters.HT, Characters.LF), LexAction.SHIFT)
        .add("space", Characters.SPACE, LexAction.SHIFT)
        .add("space", CharacterPredicate.any(), LexAction.REDUCE, "start")
        //-------------------------------------------------------------------------------
        .add("identifier", Characters.DIGIT, LexAction.SHIFT)
        .add("identifier", List.of(Characters.UPPER, CharacterPredicate.of('_'), Characters.LOWER), LexAction.SHIFT)
        .add("identifier", CharacterPredicate.any(), LexAction.REDUCE, "start")
        //-------------------------------------------------------------------------------
        .add("integer", Characters.DIGIT, LexAction.SHIFT)
        .add("integer", CharacterPredicate.any(), LexAction.REDUCE, "start")
        //-------------------------------------------------------------------------------
        .add("error", List.of(Characters.HT, Characters.LF), LexAction.REDUCE, "start")
        .add("error", Characters.CONTROL, LexAction.SHIFT)
        .add("error", Characters.ASCII, LexAction.REDUCE, "start")
        .add("error", CharacterPredicate.any(), LexAction.SHIFT)
        //-------------------------------------------------------------------------------
        .build());

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
        Tokenizer<String> tokenizer = new Tokenizer<>(LexStateMachine
            .builder("start")
            .add("start", Characters.DIGIT, LexAction.SHIFT)
            .build());
        long num = tokenizer.lex("oops").count();
        Assert.assertEquals(num, 0);
    }

    @Test
    public void limit() {
        Tokenizer<String> tokenizer = new Tokenizer<>(LexStateMachine
            .builder("start")
            .add("start", CharacterPredicate.of('o'), LexAction.SHIFT, "o")
            .add("start", CharacterPredicate.of('p'), LexAction.SHIFT_REDUCE)
            .add("start", CharacterPredicate.any(), LexAction.SHIFT)
            .add("o", CharacterPredicate.of('o'), LexAction.SHIFT)
            .add("o", CharacterPredicate.any(), LexAction.REDUCE)
            .build());

        long num = tokenizer.lex("oops").limit(1).count();
        Assert.assertEquals(num, 1);
    }

    @Test
    public void skip() {
        Tokenizer<String> tokenizer = new Tokenizer<>(LexStateMachine
            .builder("start")
            .add("start", Characters.QT, LexAction.SKIP, "string")
            .add("start", CharacterPredicate.any(), LexAction.SHIFT, "error")
            .add("string", Characters.HT, LexAction.SHIFT)
            .add("string", Characters.CONTROL, LexAction.SHIFT, "error")
            .add("string", Characters.QT, LexAction.SKIP_REDUCE, "start")
            .add("string", Characters.BACKSLASH, LexAction.SHIFT, "string-escape")
            .add("string", Characters.ASCII, LexAction.SHIFT)
            .add("string", CharacterPredicate.any(), LexAction.SHIFT, "error")
            .add("string-escape", Characters.CONTROL, LexAction.SHIFT, "error")
            .add("string-escape", Characters.ASCII, LexAction.SHIFT, "string")
            .add("string-escape", CharacterPredicate.any(), LexAction.SHIFT, "error")
            .add("error", CharacterPredicate.any(), LexAction.SHIFT)
            .build());

        List<String> tokenList = tokenizer.lex("'hello world''oops'").toList();
        Assert.assertEquals(tokenList, List.of("hello world", "oops"));
    }

    @Test
    public void tokenOption() {
        CharacterPredicate digit = CharacterPredicate.inclusive('0', '9');
        CharacterPredicate lower = CharacterPredicate.inclusive('a', 'z');
        CharacterPredicate space = CharacterPredicate.of(' ');
        CharacterPredicate upper = CharacterPredicate.inclusive('A', 'Z');

        Tokenizer<String> tokenizer = new Tokenizer<>(LexStateMachine
            .builder("start", (state, text, _) -> {
                if (text.isEmpty() || "space".equals(state) || "error".equals(state)) {
                    return Option.none();
                }
                return Option.some(text);
            })
            .add(space, LexAction.SHIFT, "space")
            .add(digit, LexAction.SHIFT, "integer")
            .add(List.of(upper, lower), LexAction.SHIFT, "identifier")
            .add(CharacterPredicate.any(), LexAction.SHIFT, "error")
            .begin("space")
            .add(space, LexAction.SHIFT)
            .add(CharacterPredicate.any(), LexAction.REDUCE, "start")
            .begin("integer")
            .add(digit, LexAction.SHIFT)
            .add(CharacterPredicate.any(), LexAction.REDUCE, "start")
            .begin("identifier")
            .add(List.of(upper, lower), LexAction.SHIFT)
            .add(CharacterPredicate.any(), LexAction.REDUCE, "start")
            .begin("error")
            .add(space, LexAction.REDUCE, "start")
            .add(digit, LexAction.REDUCE, "start")
            .add(List.of(upper, lower), LexAction.REDUCE, "start")
            .add(CharacterPredicate.any(), LexAction.SHIFT)
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
            .add("start", CharacterPredicate.any(), LexAction.SHIFT, "oops")
            .build();
    }
}
