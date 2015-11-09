package io.freefair.android.util.function;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by larsgrefer on 09.11.15.
 */
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
}