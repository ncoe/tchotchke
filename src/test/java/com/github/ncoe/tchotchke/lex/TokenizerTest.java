/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.lex;

import com.github.ncoe.tchotchke.function.CharacterPredicate;
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
        LexStateMachine<String> machine = LexStateMachine
            .builder("start")
            .add("start", Characters.DIGIT, LexAction.SHIFT)
            .build();
        Tokenizer<String> tokenizer = new Tokenizer<>(machine);
        long num = tokenizer.lex("oops").count();
        Assert.assertEquals(num, 0);
    }

    @Test
    public void limit() {
        LexStateMachine<String> machine = LexStateMachine
            .builder("start")
            .add("start", CharacterPredicate.of('o'), LexAction.SHIFT, "o")
            .add("start", CharacterPredicate.of('p'), LexAction.SHIFT_REDUCE)
            .add("start", CharacterPredicate.any(), LexAction.SHIFT)
            .add("o", CharacterPredicate.of('o'), LexAction.SHIFT)
            .add("o", CharacterPredicate.any(), LexAction.REDUCE)
            .build();
        Tokenizer<String> tokenizer = new Tokenizer<>(machine);

        long num = tokenizer.lex("oops").limit(1).count();
        Assert.assertEquals(num, 1);
    }
}
