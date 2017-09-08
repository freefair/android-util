package io.freefair.util.function;

import org.junit.Test;

import io.freefair.util.function.Suppliers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CachingSupplierTest {

	@Test
	public void testGet() throws Exception {
		Supplier innerSupplier = mock(Supplier.class);

		when(innerSupplier.get()).thenReturn("Hallo");

		Supplier cache = Suppliers.cache(innerSupplier);

		Object a = cache.get();
		Object b = cache.get();
		Object c = cache.get();

		verify(innerSupplier, atMost(1)).get();

		assertSame(a, b);
		assertSame(b, c);

		assertEquals("Hallo", a);
		assertEquals("Hallo", b);
		assertEquals("Hallo", c);
	}
}