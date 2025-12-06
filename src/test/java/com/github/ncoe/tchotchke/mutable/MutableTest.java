/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.mutable;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MutableTest {
    @SuppressWarnings({"ConstantValue", "EqualsBetweenInconvertibleTypes", "SimplifiableAssertion"})
    @Test
    public void mutBool() {
        MutableBool refA = new MutableBool();

        Assert.assertFalse(refA.getAsBoolean());
        Assert.assertEquals(refA.hashCode(), 1237);
        Assert.assertEquals(refA.toString(), "false");

        refA.accept(true);
        Assert.assertTrue(refA.getAsBoolean());
        Assert.assertEquals(refA.hashCode(), 1231);
        Assert.assertEquals(refA.toString(), "true");

        refA.setFalse();
        Assert.assertTrue(refA.isFalse());

        refA.setTrue();
        Assert.assertTrue(refA.isTrue());

        MutableBool refB = new MutableBool();

        Assert.assertEquals(refA.compareTo(refB), 1);

        Assert.assertEquals(refA.equals(null), false);
        Assert.assertEquals(refA.equals(false), false);
        Assert.assertEquals(refA.equals(true), true);
        Assert.assertEquals(refA.equals(refB), false);
        refB.setTrue();
        Assert.assertEquals(refA.equals(refB), true);
        Assert.assertEquals(refA.equals(42), false);

        Assert.assertFalse(refA.not());

        Assert.assertTrue(refA.logicalAnd(true));
        Assert.assertTrue(refA.logicalAnd(refB));

        Assert.assertTrue(refA.logicalOr(false));
        Assert.assertTrue(refA.logicalOr(refB));

        Assert.assertTrue(refA.logicalXor(false));
        Assert.assertFalse(refA.logicalXor(refB));
    }

    @SuppressWarnings({"ConstantValue", "EqualsBetweenInconvertibleTypes", "EqualsWithItself", "SimplifiableAssertion"})
    @Test
    public void mutByte() {
        MutableByte refA = new MutableByte();

        Assert.assertEquals(refA.intValue(), 0);
        Assert.assertEquals(refA.longValue(), 0);
        Assert.assertEquals(refA.floatValue(), 0);
        Assert.assertEquals(refA.doubleValue(), 0);

        Assert.assertEquals(refA.hashCode(), 0);
        Assert.assertEquals(refA.toString(), "0");

        MutableByte refB = new MutableByte((byte) 1);

        Assert.assertEquals(refA.compareTo(refB), -1);

        Assert.assertFalse(refA.equals(null));
        Assert.assertTrue(refA.equals((byte) 0));
        Assert.assertTrue(refA.equals(0.0));
        Assert.assertTrue(refA.equals(refA));
        Assert.assertFalse(refA.equals((byte) 1));
        Assert.assertFalse(refA.equals(1.0));
        Assert.assertFalse(refA.equals(refB));
        Assert.assertFalse(refA.equals('a'));

        Assert.assertEquals(refA.getAsByte(), 0);
        refA.accept((byte) 1);
        Assert.assertEquals(refA.getAsByte(), 1);

        Assert.assertEquals(refA.incrementAndGet(), 2);
        Assert.assertEquals(refA.getAndIncrement(), 2);

        Assert.assertEquals(refA.decrementAndGet(), 2);
        Assert.assertEquals(refA.getAndDecrement(), 2);

        Assert.assertEquals(refA.addAndGet((byte) 2), 3);
        Assert.assertEquals(refA.getAndAdd((byte) 2), 3);

        Assert.assertEquals(refA.accumulateAndGet((left, right) -> (byte) (left * right), (byte) 3), 15);
        Assert.assertEquals(refA.getAndAccumulate((left, right) -> (byte) (left / right), (byte) 3), 15);
    }

    @SuppressWarnings({"ConstantValue", "EqualsBetweenInconvertibleTypes", "EqualsWithItself", "SimplifiableAssertion"})
    @Test
    public void mutChar() {
        MutableChar refA = new MutableChar('a');
        Assert.assertEquals(refA.toString(), "a");

        Assert.assertEquals(refA.hashCode(), 97);

        MutableChar refB = new MutableChar('b');

        Assert.assertEquals(refA.compareTo(refB), -1);

        Assert.assertFalse(refA.equals(null));
        Assert.assertTrue(refA.equals('a'));
        Assert.assertTrue(refA.equals(refA));
        Assert.assertFalse(refA.equals('b'));
        Assert.assertFalse(refA.equals(refB));
        Assert.assertFalse(refA.equals(42));

        refA.accept('z');
        Assert.assertEquals(refA.getAsChar(), 'z');
    }

    @SuppressWarnings({"ConstantValue", "EqualsBetweenInconvertibleTypes", "EqualsWithItself", "SimplifiableAssertion"})
    @Test
    public void mutInt() {
        MutableInt refA = new MutableInt();

        Assert.assertEquals(refA.intValue(), 0);
        Assert.assertEquals(refA.longValue(), 0);
        Assert.assertEquals(refA.floatValue(), 0);
        Assert.assertEquals(refA.doubleValue(), 0);

        Assert.assertEquals(refA.hashCode(), 0);
        Assert.assertEquals(refA.toString(), "0");

        MutableInt refB = new MutableInt(1);

        Assert.assertEquals(refA.compareTo(refB), -1);

        Assert.assertFalse(refA.equals(null));
        Assert.assertTrue(refA.equals(0));
        Assert.assertTrue(refA.equals(0.0));
        Assert.assertTrue(refA.equals(refA));
        Assert.assertFalse(refA.equals(1));
        Assert.assertFalse(refA.equals(1.0));
        Assert.assertFalse(refA.equals(refB));
        Assert.assertFalse(refA.equals('a'));

        Assert.assertEquals(refA.getAsInt(), 0);
        refA.accept(1);
        Assert.assertEquals(refA.getAsInt(), 1);

        Assert.assertEquals(refA.incrementAndGet(), 2);
        Assert.assertEquals(refA.getAndIncrement(), 2);

        Assert.assertEquals(refA.decrementAndGet(), 2);
        Assert.assertEquals(refA.getAndDecrement(), 2);

        Assert.assertEquals(refA.addAndGet(2), 3);
        Assert.assertEquals(refA.getAndAdd(2), 3);

        Assert.assertEquals(refA.accumulateAndGet((left, right) -> left * right, 3), 15);
        Assert.assertEquals(refA.getAndAccumulate((left, right) -> left / right, 3), 15);
    }

    @SuppressWarnings({"ConstantValue", "EqualsBetweenInconvertibleTypes", "EqualsWithItself", "SimplifiableAssertion"})
    @Test
    public void mutLong() {
        MutableLong refA = new MutableLong();

        Assert.assertEquals(refA.intValue(), 0);
        Assert.assertEquals(refA.longValue(), 0);
        Assert.assertEquals(refA.floatValue(), 0);
        Assert.assertEquals(refA.doubleValue(), 0);

        Assert.assertEquals(refA.hashCode(), 0);
        Assert.assertEquals(refA.toString(), "0");

        MutableLong refB = new MutableLong(1);

        Assert.assertEquals(refA.compareTo(refB), -1);

        Assert.assertFalse(refA.equals(null));
        Assert.assertTrue(refA.equals(0L));
        Assert.assertTrue(refA.equals(0.0));
        Assert.assertTrue(refA.equals(refA));
        Assert.assertFalse(refA.equals(1L));
        Assert.assertFalse(refA.equals(1.0));
        Assert.assertFalse(refA.equals(refB));
        Assert.assertFalse(refA.equals('a'));

        Assert.assertEquals(refA.getAsLong(), 0);
        refA.accept(1);
        Assert.assertEquals(refA.getAsLong(), 1);

        Assert.assertEquals(refA.incrementAndGet(), 2);
        Assert.assertEquals(refA.getAndIncrement(), 2);

        Assert.assertEquals(refA.decrementAndGet(), 2);
        Assert.assertEquals(refA.getAndDecrement(), 2);

        Assert.assertEquals(refA.addAndGet(2), 3);
        Assert.assertEquals(refA.getAndAdd(2), 3);

        Assert.assertEquals(refA.accumulateAndGet((left, right) -> left * right, 3), 15);
        Assert.assertEquals(refA.getAndAccumulate((left, right) -> left / right, 3), 15);
    }

    @SuppressWarnings({"ConstantValue", "DataFlowIssue", "EqualsBetweenInconvertibleTypes", "SimplifiableAssertion"})
    @Test
    public void mutObject() {
        Mutable<String> refA = new Mutable<>();
        Assert.assertNull(refA.get());
        Assert.assertNotEquals(refA.hashCode(), 0);
        Assert.assertNotNull(refA.toString());
        Assert.assertTrue(refA.equals(null));

        Mutable<String> refB = new Mutable<>("Huge Success!!");
        Assert.assertFalse(refA.equals(refB));

        refA.accept("Huge Success!!");
        Assert.assertNotNull(refA.get());
        Assert.assertNotEquals(refA.hashCode(), 0);
        Assert.assertEquals(refA.toString(), "Huge Success!!");
        Assert.assertFalse(refA.equals("Huge Success!!"));

        Assert.assertTrue(refA.equals(refB));
    }

    @SuppressWarnings({"ConstantValue", "EqualsBetweenInconvertibleTypes", "EqualsWithItself", "SimplifiableAssertion"})
    @Test
    public void mutShort() {
        MutableShort refA = new MutableShort();

        Assert.assertEquals(refA.intValue(), 0);
        Assert.assertEquals(refA.longValue(), 0);
        Assert.assertEquals(refA.floatValue(), 0);
        Assert.assertEquals(refA.doubleValue(), 0);

        Assert.assertEquals(refA.hashCode(), 0);
        Assert.assertEquals(refA.toString(), "0");

        MutableShort refB = new MutableShort((short) 1);

        Assert.assertEquals(refA.compareTo(refB), -1);

        Assert.assertFalse(refA.equals(null));
        Assert.assertTrue(refA.equals((short) 0));
        Assert.assertTrue(refA.equals(0.0));
        Assert.assertTrue(refA.equals(refA));
        Assert.assertFalse(refA.equals((short) 1));
        Assert.assertFalse(refA.equals(1.0));
        Assert.assertFalse(refA.equals(refB));
        Assert.assertFalse(refA.equals('a'));

        Assert.assertEquals(refA.getAsShort(), 0);
        refA.accept((short) 1);
        Assert.assertEquals(refA.getAsShort(), 1);

        Assert.assertEquals(refA.incrementAndGet(), 2);
        Assert.assertEquals(refA.getAndIncrement(), 2);

        Assert.assertEquals(refA.decrementAndGet(), 2);
        Assert.assertEquals(refA.getAndDecrement(), 2);

        Assert.assertEquals(refA.addAndGet((short) 2), 3);
        Assert.assertEquals(refA.getAndAdd((short) 2), 3);

        Assert.assertEquals(refA.accumulateAndGet((left, right) -> (short) (left * right), (short) 3), 15);
        Assert.assertEquals(refA.getAndAccumulate((left, right) -> (short) (left / right), (short) 3), 15);
    }

    @Test
    public void mutCompare() {
        MutableCompare<String> refA = new MutableCompare<>();
        Assert.assertEquals(refA.compareTo(null), 1);

        MutableCompare<String> refB = new MutableCompare<>();
        Assert.assertEquals(refA.compareTo(refB), 0);

        refB.accept("rhs");
        Assert.assertEquals(refA.compareTo(refB), -1);

        refA.accept("lhs");
        Assert.assertEquals(refA.compareTo(refB), -6);
    }
}
