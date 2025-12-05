/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.function;

import org.testng.Assert;
import org.testng.annotations.Test;

public class UnaryOperatorTest {
    @Test
    public void byteOperator() {
        ByteUnaryOperator op1 = b -> (byte) (b + 1);
        byte actual1 = op1.applyAsByte((byte) 1);
        Assert.assertEquals(actual1, 2);

        ByteUnaryOperator op2 = ByteUnaryOperator.identity().compose(op1).andThen(b -> (byte) (b + 2));
        byte actual2 = op2.applyAsByte((byte) 1);
        Assert.assertEquals(actual2, 4);
    }

    @Test
    public void charOperator() {
        CharUnaryOperator op1 = b -> (char) (b + 1);
        char actual1 = op1.applyAsChar((char) 1);
        Assert.assertEquals(actual1, (char) 2);

        CharUnaryOperator op2 = CharUnaryOperator.identity().compose(op1).andThen(b -> (char) (b + 2));
        char actual2 = op2.applyAsChar((char) 1);
        Assert.assertEquals(actual2, (char) 4);
    }

    @Test
    public void shortOperator() {
        ShortUnaryOperator op1 = s -> (short) (s + 1);
        short actual1 = op1.applyAsShort((short) 1);
        Assert.assertEquals(actual1, 2);

        ShortUnaryOperator op2 = ShortUnaryOperator.identity().compose(op1).andThen(s -> (short) (s + 2));
        short actual2 = op2.applyAsShort((short) 1);
        Assert.assertEquals(actual2, 4);
    }
}
