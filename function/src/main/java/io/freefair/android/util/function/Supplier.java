package io.freefair.android.util.function;

import android.support.annotation.Nullable;

/**
 * Represents a supplier of results.
 * <p/>
 * There is no requirement that a new or distinct result be returned each time the supplier is invoked.
 *
 * @param <T> the type of results supplied by this supplier
 * @see <a href="http://docs.oracle.com/javase/8/docs/api/java/util/function/Supplier.html">http://docs.oracle.com/javase/8/docs/api/java/util/function/Supplier.html</a>
 */
public interface Supplier<T> {

	/**
	 * Get a result.
	 *
	 * @return a result
	 */
	@Nullable
	T get();
}
