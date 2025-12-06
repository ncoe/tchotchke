/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.tuple;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TupleTest {
    @Test
    public void pair() {
        Pair<Integer, Integer> p = new Pair<>(1, 2);
        Assert.assertNotNull(p.x());
        Assert.assertNotNull(p.y());
    }

    @Test
    public void triple() {
        var t = new Triple<>(1, 2, 3);
        Assert.assertNotNull(t.x());
        Assert.assertNotNull(t.y());
        Assert.assertNotNull(t.z());
    }
}
