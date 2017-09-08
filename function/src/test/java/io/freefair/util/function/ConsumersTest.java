package io.freefair.util.function;

import android.support.annotation.Nullable;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConsumersTest {

    @Test
    public void testChain() throws Exception {
        StringBuilder sb = new StringBuilder();

        Consumer<StringBuilder> first = new Consumer<StringBuilder>() {
            @Override
            public void accept(@Nullable StringBuilder value) {
                if (value != null) {
                    value.append("a");
                }
            }
        };

        Consumer<StringBuilder> second = new Consumer<StringBuilder>() {
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