package io.freefair.util.function;

import android.support.annotation.Nullable;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

public class FunctionsTest {

    @Test
    public void testIdentity() throws Exception {
        Collection<Object> objects = new ArrayList<>();

        objects.add("Hallo");
        objects.add(55);
        objects.add(new ArithmeticException());

        Function<Object, Object> identity = Functions.identity();

        for (Object obj : objects) {
            assertThat(identity.apply(obj)).isSameAs(obj);
        }

    }

    @Test
    public void testChain() throws Exception {
        Function<Integer, Integer> a = new Function<Integer, Integer>() {
            @Nullable
            @Override
            public Integer apply(@Nullable Integer value) {
                return value * 3;
            }
        };

        Function<Integer, Integer> b = new Function<Integer, Integer>() {
            @Nullable
            @Override
            public Integer apply(@Nullable Integer value) {
                return value + 3;
            }
        };

        Function<Integer, Integer> chain1 = Functions.chain(a, b);
        Function<Integer, Integer> chain2 = Functions.chain(b, a);

        for (int i = -3; i < 3; i++) {
            assertThat(chain1.apply(i)).isEqualTo((i * 3) + 3);
            assertThat(chain2.apply(i)).isEqualTo((i + 3) * 3);
        }

    }
}