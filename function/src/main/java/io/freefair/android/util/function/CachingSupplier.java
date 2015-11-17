package io.freefair.android.util.function;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public abstract class CachingSupplier<T> implements Supplier<T> {

	/**
	 * The cache
	 */
	@Nullable
	private T object;

	/**
	 * If the cache is filled
	 */
	private boolean created = false;

	@Override
	@Nullable
	public T get() {
		if (!created) {
			object = create();
			created = true;
		}
		return object;
	}

	/**
	 * Create the result that should be cached.
	 * This method is called only once.
	 *
	 * @return The result, which will be cached.
	 */
	@Nullable
	protected abstract T create();

	/**
	 * @see Suppliers#cache(Supplier)
	 */
	@NonNull
	public static <X> CachingSupplier<X> of(final Supplier<X> innerSupplier) {
		return new CachingSupplier<X>() {
			@Override
			protected X create() {
				return innerSupplier.get();
			}
		};
	}
}
