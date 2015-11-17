package io.freefair.android.util.function;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class OptionalTest {

	Optional empty;
	Optional notEmpty;
	Optional notEmpty2;

	@Before
	public void setUp() throws Exception {
		empty = Optional.empty();
		notEmpty = Optional.of("Hallo");
		notEmpty2 = Optional.of(2);
	}

	@Test
	public void testIsPresent() throws Exception {
		assertFalse(empty.isPresent());
		assertTrue(notEmpty.isPresent());
		assertTrue(notEmpty2.isPresent());
	}

	@Test
	public void testGet() throws Exception {
		assertNotNull(notEmpty.get());
		assertNotNull(notEmpty2.get());
	}

	@Test(expected = NoSuchElementException.class)
	public void testGetOnEmtpy() throws Exception {
		empty.get();
	}

	@Test
	public void testOrNull() throws Exception {
		assertNull(empty.orNull());
		assertNotNull(notEmpty.orNull());
		assertNotNull(notEmpty2.orNull());
	}

	@Test
	public void testOrElse() throws Exception {
		Object _else = "B";

		assertEquals(_else, empty.orElse(_else));
		assertNotEquals(_else, notEmpty.orElse(_else));
		assertNotEquals(_else, notEmpty2.orElse(_else));
	}

	@Test
	public void testOrElseGet() throws Exception {

	}

	@Test
	public void testOrElseThrow() throws Exception {

	}

	@Test
	public void testFilter() throws Exception {
		assertFalse(empty.filter(Predicates.alwaysTrue()).isPresent());
		assertTrue(notEmpty.filter(Predicates.alwaysTrue()).isPresent());
		assertFalse(notEmpty.filter(Predicates.alwaysFalse()).isPresent());
	}

	@Test
	public void testMap() throws Exception {

	}

	@Test
	public void testFlatMap() throws Exception {

	}

	@Test
	public void testEquals() throws Exception {
		assertTrue(empty.equals(Optional.empty()));
		assertFalse(notEmpty.equals(notEmpty2));
		assertFalse(empty.equals(notEmpty));
	}

	@Test
	public void testHashCode() throws Exception {
		assertEquals(0, empty.hashCode());
		assertEquals(notEmpty.get().hashCode(), notEmpty.hashCode());
	}

	@Test
	public void testEmpty() throws Exception {
		assertFalse(Optional.empty().isPresent());
	}
}