/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.util;

import org.testng.Assert;
import org.testng.annotations.Test;

@SuppressWarnings("DataFlowIssue")
public class AssertionTest {
    @Test
    public void condition() {
        Assertion.isFalse(false, "oops");
        Assert.assertThrows(IllegalArgumentException.class, () -> Assertion.isFalse(true, "oops"));

        Assertion.isTrue(true, "oops");
        Assert.assertThrows(IllegalArgumentException.class, () -> Assertion.isTrue(false, "oops"));
    }

    @Test
    public void check() {
        Assertion.isNull(null, "oops");
        Assert.assertThrows(IllegalArgumentException.class, () -> Assertion.isNull("hello", "oops"));

        Assertion.notNull("hello", "oops");
        Assert.assertThrows(IllegalArgumentException.class, () -> Assertion.notNull(null, "oops"));

        Assertion.isSame("hello", "hello", "oops");
        Assert.assertThrows(IllegalArgumentException.class, () -> Assertion.isSame("hello", "world", "oops"));

        Assertion.isNotSame("hello", "world", "oops");
        Assert.assertThrows(IllegalArgumentException.class, () -> Assertion.isNotSame("hello", "hello", "oops"));

        Assertion.isEqual(null, null, "oops");
        Assert.assertThrows(IllegalArgumentException.class, () -> Assertion.isEqual(null, 2, "oops"));
        Assert.assertThrows(IllegalArgumentException.class, () -> Assertion.isEqual(1, null, "oops"));
        Assertion.isEqual(1, 1, "oops");
        Assert.assertThrows(IllegalArgumentException.class, () -> Assertion.isEqual(1, 2, "oops"));

        Assertion.isNotEqual(null, 2, "oops");
        Assertion.isNotEqual(1, null, "oops");
        Assert.assertThrows(IllegalArgumentException.class, () -> Assertion.isNotEqual(null, null, "oops"));
        Assertion.isNotEqual(1, 2, "oops");
        Assert.assertThrows(IllegalArgumentException.class, () -> Assertion.isNotEqual(1, 1, "oops"));

        Assertion.isLess(null, 2, "oops");
        Assert.assertThrows(IllegalArgumentException.class, () -> Assertion.isLess(2, null, "oops"));
        Assertion.isLess(1, 2, "oops");
        Assert.assertThrows(IllegalArgumentException.class, () -> Assertion.isLess(2, 1, "oops"));

        Assertion.isLessOrEqual(null, null, "oops");
        Assertion.isLessOrEqual(null, 2, "oops");
        Assert.assertThrows(IllegalArgumentException.class, () -> Assertion.isLessOrEqual(2, null, "oops"));
        Assertion.isLessOrEqual(1, 1, "oops");
        Assertion.isLessOrEqual(1, 2, "oops");
        Assert.assertThrows(IllegalArgumentException.class, () -> Assertion.isLessOrEqual(2, 1, "oops"));

        Assertion.isGreater(2, null, "oops");
        Assert.assertThrows(IllegalArgumentException.class, () -> Assertion.isGreater(null, 2, "oops"));
        Assertion.isGreater(2, 1, "oops");
        Assert.assertThrows(IllegalArgumentException.class, () -> Assertion.isGreater(1, 2, "oops"));

        Assertion.isGreaterOrEqual(null, null, "oops");
        Assertion.isGreaterOrEqual(2, null, "oops");
        Assert.assertThrows(IllegalArgumentException.class, () -> Assertion.isGreaterOrEqual(null, 2, "oops"));
        Assertion.isGreaterOrEqual(1, 1, "oops");
        Assertion.isGreaterOrEqual(2, 1, "oops");
        Assert.assertThrows(IllegalArgumentException.class, () -> Assertion.isGreaterOrEqual(1, 2, "oops"));
    }
}
