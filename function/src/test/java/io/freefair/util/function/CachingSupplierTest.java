package io.freefair.util.function;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

        assertThat(a).isSameAs(b);
        assertThat(b).isSameAs(c);

        assertThat(a).isEqualTo("Hallo");
        assertThat(b).isEqualTo("Hallo");
        assertThat(c).isEqualTo("Hallo");
    }
}