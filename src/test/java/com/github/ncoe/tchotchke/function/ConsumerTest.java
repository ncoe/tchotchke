/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.function;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ConsumerTest {
    private void consumeBool(BooleanConsumer consumer, boolean value) {
        consumer.andThen(consumer).accept(value);
    }

    @Test
    public void consumeBool() {
        consumeBool(Assert::assertFalse, false);
        consumeBool(Assert::assertTrue, true);
    }

    private void consumeByte(ByteConsumer consumer, byte value) {
        consumer.andThen(consumer).accept(value);
    }

    @Test
    public void consumeByte() {
        consumeByte(value -> Assert.assertEquals(value, 0), (byte) 0);
        consumeByte(value -> Assert.assertEquals(value, 1), (byte) 1);
    }

    private void consumeChar(CharacterConsumer consumer, char ch) {
        consumer.andThen(consumer).accept(ch);
    }

    @Test
    public void consumeChar() {
        consumeChar(value -> Assert.assertEquals(value, 'A'), 'A');
        consumeChar(value -> Assert.assertEquals(value, 'z'), 'z');
    }

    private void consumeShort(ShortConsumer consumer, short value) {
        consumer.andThen(consumer).accept(value);
    }

    @Test
    public void consumeShort() {
        consumeShort(value -> Assert.assertEquals(value, 0), (short) 0);
        consumeShort(value -> Assert.assertEquals(value, 1), (short) 1);
    }
}
