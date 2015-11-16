package io.freefair.android.util.function;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public abstract class CachingSupplier<T> implements Supplier<T> {

	private T object;

	@Override
	@Nullable
	public T get() {
		if (object == null)
			object = create();
		return object;
	}

	@Nullable
	protected abstract T create();

	@NonNull
	public static <X> CachingSupplier<X> of(final Supplier<X> baseSupplier) {
		return new CachingSupplier<X>() {
			@Override
			protected X create() {
				return baseSupplier.get();
			}
		};
	}
}
