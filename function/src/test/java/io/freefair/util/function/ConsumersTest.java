package io.freefair.util.function;

import org.jetbrains.annotations.Nullable;
import org.junit.Test;

import io.freefair.util.function.Consumers;

import static org.junit.Assert.assertEquals;

public class ConsumersTest {

    @Test
    public void testChain() throws Exception {
        StringBuilder sb = new StringBuilder();

        io.freefair.util.function.Consumer<StringBuilder> first = new io.freefair.util.function.Consumer<StringBuilder>() {
            @Override
            public void accept(@Nullable StringBuilder value) {
                if (value != null) {
                    value.append("a");
                }
            }
        };

        io.freefair.util.function.Consumer<StringBuilder> second = new io.freefair.util.function.Consumer<StringBuilder>() {
            @Override
            public void accept(@Nullable StringBuilder value) {
                if (value != null) {
                    value.append("b");
                }
            }
        };

        Consumers.chain(first, second).accept(sb);

        assertEquals("ab", sb.toString());
    }
}