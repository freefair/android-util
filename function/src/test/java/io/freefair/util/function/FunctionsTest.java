package io.freefair.util.function;

import org.jetbrains.annotations.Nullable;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import io.freefair.util.function.Functions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class FunctionsTest {

	@Test
	public void testIdentity() throws Exception {
		Collection<Object> objects = new ArrayList<>();

		objects.add("Hallo");
		objects.add(55);
		objects.add(new ArithmeticException());

		io.freefair.util.function.Function<Object, Object> identity = io.freefair.util.function.Functions.identity();

		for (Object obj : objects) {
			assertSame(obj, identity.apply(obj));
		}

	}

	@Test
	public void testChain() throws Exception {
		io.freefair.util.function.Function<Integer, Integer> a = new io.freefair.util.function.Function<Integer, Integer>() {
			@Nullable
			@Override
			public Integer apply(@Nullable Integer value) {
				return value * 3;
			}
		};

		io.freefair.util.function.Function<Integer, Integer> b = new io.freefair.util.function.Function<Integer, Integer>() {
			@Nullable
			@Override
			public Integer apply(@Nullable Integer value) {
				return value + 3;
			}
		};

		io.freefair.util.function.Function<Integer, Integer> chain1 = io.freefair.util.function.Functions.chain(a, b);
		io.freefair.util.function.Function<Integer, Integer> chain2 = Functions.chain(b, a);

		for (int i = -3; i < 3; i++) {
			assertEquals((i*3)+3,(int)chain1.apply(i));
			assertEquals((i+3)*3,(int)chain2.apply(i));
		}

	}
}