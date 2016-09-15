package io.freefair.util.function;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import io.freefair.util.function.Predicates;

import static org.junit.Assert.*;

public class OptionalTest {

	io.freefair.util.function.Optional empty;
	io.freefair.util.function.Optional notEmpty;
	io.freefair.util.function.Optional notEmpty2;

	@Before
	public void setUp() throws Exception {
		empty = io.freefair.util.function.Optional.empty();
		notEmpty = io.freefair.util.function.Optional.of("Hallo");
		notEmpty2 = io.freefair.util.function.Optional.of(2);
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
		assertFalse(empty.filter(io.freefair.util.function.Predicates.alwaysTrue()).isPresent());
		assertTrue(notEmpty.filter(io.freefair.util.function.Predicates.alwaysTrue()).isPresent());
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
		assertTrue(empty.equals(io.freefair.util.function.Optional.empty()));
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
		assertFalse(io.freefair.util.function.Optional.empty().isPresent());
	}
}