package io.freefair.util.function;

import org.jetbrains.annotations.NotNull;

/**
 * Static methods for creating {@link Supplier Suppliers}
 */
@SuppressWarnings("unused")
public final class Suppliers {

	/**
	 * No instances
	 */
	private Suppliers() {
	}

	/**
	 * Create a {@link Supplier} which supplies the given object
	 *
	 * @param object The object to supply
	 * @return A {@link Supplier} supplying the given object
	 */
	@NotNull
	public static <T> Supplier<T> of(final T object) {
		return new Supplier<T>() {
			@Override
			public T get() {
				return object;
			}
		};
	}

	/**
	 * Create a {@link Supplier} which caches the value returned by the given supplier.
	 *
	 * @param supplier The supplier to cache. {@link Supplier#get() get()} will only called once on this.
	 */
	@NotNull
	public static <T> Supplier<T> cache(final Supplier<T> supplier) {
		return io.freefair.util.function.CachingSupplier.of(supplier);
	}
}
