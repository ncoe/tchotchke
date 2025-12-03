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
    }
}
