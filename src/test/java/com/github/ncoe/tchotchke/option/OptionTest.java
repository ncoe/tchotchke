/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.option;

import com.github.ncoe.tchotchke.function.Functions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.NoSuchElementException;

public class OptionTest {
    @Test
    public void coverT() {
        Option.some("Hello World").ifPresent(value -> Assert.assertEquals(value, "Hello World"));
        Option.some("Hello World").ifPresentOrElse(value -> Assert.assertEquals(value, "Hello World"), Assert::fail);
        Assert.assertEquals(Option.some("Hello World").stream().count(), 1);
        Assert.assertEquals(Option.some("Hello World").orElse("Oops"), "Hello World");
        Assert.assertEquals(Option.some("Hello World").orElseGet(() -> "Oops"), "Hello World");
        Assert.assertEquals(Option.some("Hello World").orElseThrow(), "Hello World");
        Assert.assertEquals(Option.some("Hello World").orElseThrow(IllegalArgumentException::new), "Hello World");

        Option.none().ifPresent(_ -> Assert.fail());
        Option.none().ifPresentOrElse(_ -> Assert.fail(), Functions.doNothing());
        Assert.assertEquals(Option.none().stream().count(), 0);
        Assert.assertEquals(Option.none().orElse("Oops"), "Oops");
        Assert.assertEquals(Option.none().orElseGet(() -> "Oops"), "Oops");
        Assert.assertThrows(NoSuchElementException.class, () -> Option.none().orElseThrow());
        Assert.assertThrows(IllegalArgumentException.class, () -> Option.none().orElseThrow(IllegalArgumentException::new));
    }

    @Test
    public void coverBool() {
        OptionBool.some(true).ifPresent(Assert::assertTrue);
        OptionBool.some(true).ifPresentOrElse(Assert::assertTrue, Assert::fail);
        Assert.assertEquals(OptionBool.some(true).stream().count(), 1);
        Assert.assertTrue(OptionBool.some(true).orElse(false));
        Assert.assertTrue(OptionBool.some(true).orElseGet(() -> false));
        Assert.assertTrue(OptionBool.some(true).orElseThrow());
        Assert.assertTrue(OptionBool.some(true).orElseThrow(IllegalArgumentException::new));

        OptionBool.none().ifPresent(_ -> Assert.fail());
        OptionBool.none().ifPresentOrElse(_ -> Assert.fail(), Functions.doNothing());
        Assert.assertEquals(OptionBool.none().stream().count(), 0);
        Assert.assertFalse(OptionBool.none().orElse(false));
        Assert.assertFalse(OptionBool.none().orElseGet(() -> false));
        Assert.assertThrows(NoSuchElementException.class, () -> OptionBool.none().orElseThrow());
        Assert.assertThrows(IllegalArgumentException.class, () -> OptionBool.none().orElseThrow(IllegalArgumentException::new));
    }

    @Test
    public void coverChar() {
        OptionChar.some('a').ifPresent(value -> Assert.assertEquals(value, 'a'));
        OptionChar.some('b').ifPresentOrElse(value -> Assert.assertEquals(value, 'b'), Assert::fail);
        Assert.assertEquals(OptionChar.some('c').stream().count(), 1);
        Assert.assertEquals(OptionChar.some('d').orElse('e'), 'd');
        Assert.assertEquals(OptionChar.some('e').orElseGet(() -> 'f'), 'e');
        Assert.assertEquals(OptionChar.some('f').orElseThrow(), 'f');
        Assert.assertEquals(OptionChar.some('g').orElseThrow(IllegalArgumentException::new), 'g');

        OptionChar.none().ifPresent(_ -> Assert.fail());
        OptionChar.none().ifPresentOrElse(_ -> Assert.fail(), Functions.doNothing());
        Assert.assertEquals(OptionChar.none().stream().count(), 0);
        Assert.assertEquals(OptionChar.none().orElse('a'), 'a');
        Assert.assertEquals(OptionChar.none().orElseGet(() -> 'b'), 'b');
        Assert.assertThrows(NoSuchElementException.class, () -> OptionChar.none().orElseThrow());
        Assert.assertThrows(IllegalArgumentException.class, () -> OptionChar.none().orElseThrow(IllegalArgumentException::new));
    }

    @Test
    public void coverInt() {
        OptionInt.some(42).ifPresent(value -> Assert.assertEquals(value, 42));
        OptionInt.some(42).ifPresentOrElse(value -> Assert.assertEquals(value, 42), Assert::fail);
        Assert.assertEquals(OptionInt.some(42).stream().count(), 1);
        Assert.assertEquals(OptionInt.some(42).orElse(154), 42);
        Assert.assertEquals(OptionInt.some(42).orElseGet(() -> 154), 42);
        Assert.assertEquals(OptionInt.some(42).orElseThrow(), 42);
        Assert.assertEquals(OptionInt.some(42).orElseThrow(IllegalArgumentException::new), 42);

        OptionInt.none().ifPresent(_ -> Assert.fail());
        OptionInt.none().ifPresentOrElse(_ -> Assert.fail(), Functions.doNothing());
        Assert.assertEquals(OptionInt.none().stream().count(), 0);
        Assert.assertEquals(OptionInt.none().orElse(154), 154);
        Assert.assertEquals(OptionInt.none().orElseGet(() -> 154), 154);
        Assert.assertThrows(NoSuchElementException.class, () -> OptionInt.none().orElseThrow());
        Assert.assertThrows(IllegalArgumentException.class, () -> OptionInt.none().orElseThrow(IllegalArgumentException::new));
    }

    @Test
    public void coverLong() {
        OptionLong.some(42).ifPresent(value -> Assert.assertEquals(value, 42));
        OptionLong.some(42).ifPresentOrElse(value -> Assert.assertEquals(value, 42), Assert::fail);
        Assert.assertEquals(OptionLong.some(42).stream().count(), 1);
        Assert.assertEquals(OptionLong.some(42).orElse(154), 42);
        Assert.assertEquals(OptionLong.some(42).orElseGet(() -> 154), 42);
        Assert.assertEquals(OptionLong.some(42).orElseThrow(), 42);
        Assert.assertEquals(OptionLong.some(42).orElseThrow(IllegalArgumentException::new), 42);

        OptionLong.none().ifPresent(_ -> Assert.fail());
        OptionLong.none().ifPresentOrElse(_ -> Assert.fail(), Functions.doNothing());
        Assert.assertEquals(OptionLong.none().stream().count(), 0);
        Assert.assertEquals(OptionLong.none().orElse(154), 154);
        Assert.assertEquals(OptionLong.none().orElseGet(() -> 154), 154);
        Assert.assertThrows(NoSuchElementException.class, () -> OptionLong.none().orElseThrow());
        Assert.assertThrows(IllegalArgumentException.class, () -> OptionLong.none().orElseThrow(IllegalArgumentException::new));
    }
}
