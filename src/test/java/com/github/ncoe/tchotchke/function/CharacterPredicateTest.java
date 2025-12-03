/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.function;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CharacterPredicateTest {
    private static boolean evaluate(CharacterPredicate predicate, char ch) {
        return predicate.test(ch);
    }

    @Test
    public void cover() {
        CharacterPredicate pA = ch -> ch == 'A';
        CharacterPredicate pAn = pA.negate();
        Assert.assertFalse(evaluate(pAn, 'A'));
        Assert.assertTrue(evaluate(pAn, 'a'));

        CharacterPredicate pA2 = ch -> 'A' <= ch;
        CharacterPredicate pUpper = pA2.and(ch -> ch <= 'Z');
        Assert.assertFalse(evaluate(pUpper, '0'));
        Assert.assertFalse(evaluate(pUpper, 'a'));
        Assert.assertTrue(evaluate(pUpper, 'F'));

        CharacterPredicate pS = ch -> ch == '$';
        CharacterPredicate pSS = pS.or(ch -> ch == '@');
        Assert.assertFalse(evaluate(pSS, '!'));
        Assert.assertTrue(evaluate(pSS, '$'));
        Assert.assertTrue(evaluate(pSS, '@'));
    }
}
