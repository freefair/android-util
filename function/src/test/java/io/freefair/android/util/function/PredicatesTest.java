package io.freefair.android.util.function;

import org.junit.Test;

import static org.junit.Assert.*;

public class PredicatesTest {

	@Test
	public void testAlwaysTrue() {
		Predicate<Object> alwaysTrue = Predicates.alwaysTrue();

		assertTrue(alwaysTrue.test(null));
		assertTrue(alwaysTrue.test("Hallo"));
		assertTrue(alwaysTrue.test(55l));
	}

	@Test
	public void testAlwaysFalse() {
		Predicate<Object> alwaysFalse = Predicates.alwaysFalse();

		assertFalse(alwaysFalse.test(null));
		assertFalse(alwaysFalse.test("FooBar"));
		assertFalse(alwaysFalse.test(66d));
	}

	@Test
	public void testNotNull() throws Exception {
		Predicate<Object> notNull = Predicates.notNull();

		assertTrue(notNull.test("Hallo"));
		assertFalse(notNull.test(null));
	}

	@Test
	public void testIsNull() throws Exception {
		Predicate<Object> isNull = Predicates.isNull();

		assertTrue(isNull.test(null));
		assertFalse(isNull.test("test"));
	}
}