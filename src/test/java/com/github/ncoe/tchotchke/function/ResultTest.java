/*
 * Copyright (c) 2025.
 */

package com.github.ncoe.tchotchke.function;

import com.github.ncoe.tchotchke.util.Assertion;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.function.Consumer;

public class ResultTest {
    private static <T, E> void cover(Result<T, E> result, Consumer<T> okConsumer, Consumer<E> errorConsumer) {
        Assertion.notNull(result, "result is required");
        switch (result) {
            case Result.Ok<T, E> ok -> okConsumer.accept(ok.value());
            case Result.Error<T, E> error -> errorConsumer.accept(error.error());
        }
    }

    @Test
    public void cover() {
        cover(
            Result.ok(42),
            value -> Assert.assertEquals(value, 42),
            _ -> Assert.fail("Unexpected error")
        );
        cover(
            Result.ok("Hello"),
            value -> Assert.assertEquals(value, "Hello"),
            _ -> Assert.fail("Unexpected error")
        );

        cover(
            Result.error(154),
            _ -> Assert.fail("Unexpected success"),
            error -> Assert.assertEquals(error, 154)
        );
        cover(
            Result.error("Oops"),
            _ -> Assert.fail("Unexpected success"),
            error -> Assert.assertEquals(error, "Oops")
        );
    }
}
