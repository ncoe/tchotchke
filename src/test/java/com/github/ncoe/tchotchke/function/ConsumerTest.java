/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.function;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ConsumerTest {
    private void consume(BooleanConsumer consumer, boolean value) {
        consumer.andThen(consumer).accept(value);
    }

    @Test
    public void consumeBool() {
        consume(Assert::assertFalse, false);
        consume(Assert::assertTrue, true);
    }

    private void consume(CharacterConsumer consumer, char ch) {
        consumer.andThen(consumer).accept(ch);
    }

    @Test
    public void consumeChar() {
        consume(value -> Assert.assertEquals(value, 'A'), 'A');
        consume(value -> Assert.assertEquals(value, 'z'), 'z');
    }
}
