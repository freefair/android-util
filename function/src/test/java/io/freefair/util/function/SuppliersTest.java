package io.freefair.util.function;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by larsgrefer on 18.11.15.
 */
public class SuppliersTest {

	@Test
	public void testOf() throws Exception {
		Collection<Object> objects = new ArrayList<>();

		objects.add("Hallo");
		objects.add(55);
		objects.add(new ArithmeticException());

		for (Object obj : objects) {
			assertSame(obj, Suppliers.of(obj).get());
		}


	}
}