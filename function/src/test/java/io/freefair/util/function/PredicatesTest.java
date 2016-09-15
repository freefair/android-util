package io.freefair.util.function;

import org.junit.Test;

import io.freefair.util.function.Predicates;

import static org.junit.Assert.*;

public class PredicatesTest {

	@Test
	public void testAlwaysTrue() {
		io.freefair.util.function.Predicate<Object> alwaysTrue = io.freefair.util.function.Predicates.alwaysTrue();

		assertTrue(alwaysTrue.test(null));
		assertTrue(alwaysTrue.test("Hallo"));
		assertTrue(alwaysTrue.test(55l));
	}

	@Test
	public void testAlwaysFalse() {
		io.freefair.util.function.Predicate<Object> alwaysFalse = io.freefair.util.function.Predicates.alwaysFalse();

		assertFalse(alwaysFalse.test(null));
		assertFalse(alwaysFalse.test("FooBar"));
		assertFalse(alwaysFalse.test(66d));
	}

	@Test
	public void testNotNull() throws Exception {
		io.freefair.util.function.Predicate<Object> notNull = io.freefair.util.function.Predicates.notNull();

		assertTrue(notNull.test("Hallo"));
		assertFalse(notNull.test(null));
	}

	@Test
	public void testIsNull() throws Exception {
		io.freefair.util.function.Predicate<Object> isNull = Predicates.isNull();

		assertTrue(isNull.test(null));
		assertFalse(isNull.test("test"));
	}
}