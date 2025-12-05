/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.function;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PredicateTest {
    private static boolean evaluateByte(BytePredicate predicate, byte value) {
        return predicate.test(value);
    }

    @Test
    public void predicateByte() {
        BytePredicate pA = b -> b == 0;
        BytePredicate pAn = pA.negate();
        Assert.assertFalse(evaluateByte(pAn, (byte) 0));
        Assert.assertTrue(evaluateByte(pAn, (byte) 1));

        BytePredicate pA2 = b -> 30 <= b;
        BytePredicate pB = pA2.and(b -> b <= 39);
        Assert.assertFalse(evaluateByte(pB, (byte) 20));
        Assert.assertFalse(evaluateByte(pB, (byte) 40));
        Assert.assertTrue(evaluateByte(pB, (byte) 35));

        BytePredicate pS = b -> b == 42;
        BytePredicate pSS = pS.or(b -> b == 62);
        Assert.assertFalse(evaluateByte(pSS, (byte) 21));
        Assert.assertTrue(evaluateByte(pSS, (byte) 42));
        Assert.assertTrue(evaluateByte(pSS, (byte) 62));
    }

    private static boolean evaluateChar(CharacterPredicate predicate, char ch) {
        return predicate.test(ch);
    }

    @Test
    public void predicateChar() {
        CharacterPredicate pA = ch -> ch == 'A';
        CharacterPredicate pAn = pA.negate();
        Assert.assertFalse(evaluateChar(pAn, 'A'));
        Assert.assertTrue(evaluateChar(pAn, 'a'));

        CharacterPredicate pA2 = ch -> 'A' <= ch;
        CharacterPredicate pUpper = pA2.and(ch -> ch <= 'Z');
        Assert.assertFalse(evaluateChar(pUpper, '0'));
        Assert.assertFalse(evaluateChar(pUpper, 'a'));
        Assert.assertTrue(evaluateChar(pUpper, 'F'));

        CharacterPredicate pS = ch -> ch == '$';
        CharacterPredicate pSS = pS.or(ch -> ch == '@');
        Assert.assertFalse(evaluateChar(pSS, '!'));
        Assert.assertTrue(evaluateChar(pSS, '$'));
        Assert.assertTrue(evaluateChar(pSS, '@'));
    }

    private static boolean evaluateShort(ShortPredicate predicate, short value) {
        return predicate.test(value);
    }

    @Test
    public void predicateShort() {
        ShortPredicate pA = b -> b == 0;
        ShortPredicate pAn = pA.negate();
        Assert.assertFalse(evaluateShort(pAn, (byte) 0));
        Assert.assertTrue(evaluateShort(pAn, (byte) 1));

        ShortPredicate pA2 = b -> 30 <= b;
        ShortPredicate pB = pA2.and(b -> b <= 39);
        Assert.assertFalse(evaluateShort(pB, (byte) 20));
        Assert.assertFalse(evaluateShort(pB, (byte) 40));
        Assert.assertTrue(evaluateShort(pB, (byte) 35));

        ShortPredicate pS = b -> b == 42;
        ShortPredicate pSS = pS.or(b -> b == 62);
        Assert.assertFalse(evaluateShort(pSS, (byte) 21));
        Assert.assertTrue(evaluateShort(pSS, (byte) 42));
        Assert.assertTrue(evaluateShort(pSS, (byte) 62));
    }
}
