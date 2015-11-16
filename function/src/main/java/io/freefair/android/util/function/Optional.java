package io.freefair.android.util.function;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * A container object which may or may not contain a non-null value.
 * If a value is present, {@link #isPresent()} will return true and {@link #get()} will return the value.
 * <p/>
 * Additional methods that depend on the presence or absence of a contained value are provided,
 * such as {@link #orElse(Object)} (return a default value if value not present).
 * <p/>
 * This is a value-based class; use of identity-sensitive operations (including reference equality (==), identity hash code, or synchronization) on instances of Optional may have unpredictable results and should be avoided.
 *
 * @param <T> The type of the contained value.
 * @see <a href="http://docs.oracle.com/javase/8/docs/api/java/util/Optional.html">http://docs.oracle.com/javase/8/docs/api/java/util/Optional.html</a>
 */
@SuppressWarnings("unused")
public class Optional<T> {

	private final T object;

	/**
	 * @see #of(Object)
	 * @see #ofNullable(Object)
	 */
	private Optional(T obj) {
		object = obj;
	}

	/**
	 * Check if a value is present.
	 *
	 * @return true, if a value is present, false if this Optional is empty
	 */
	public boolean isPresent() {
		return object != null;
	}

	/**
	 * @return The value, if its present
	 * @throws IllegalStateException If the Optional is empty
	 */
	public T get() throws IllegalStateException {
		if (isPresent())
			return object;
		else {
			throw new IllegalStateException("This optional is empty");
		}
	}

	/**
	 * @return The value, if its present, or else null
	 */
	@Nullable
	public T orNull() {
		return object;
	}

	/**
	 * @param alternative The alternative to use, if this Optional is empty
	 * @return The value, if its present, or else the alternative
	 */
	public T orElse(T alternative) {
		return isPresent() ? object : alternative;
	}

	/**
	 * Maps the value of this {@link Optional} using the given Function
	 * and wraps the new value into a new {@link Optional}
	 * <p/>
	 * If this {@link Optional} is empty, the new {@link Optional} will be empty too.
	 * If the given function returns null, the new {@link Optional} will be empty.
	 *
	 * @param function The function for mapping the
	 * @param <V>      The type of the new {@link Optional}
	 * @return The new {@link Optional}
	 * @throws IllegalArgumentException If function is null
	 */
	@NonNull
	public <V> Optional<V> map(@NonNull Function<? super T, V> function) throws IllegalArgumentException {
		if (function == null)
			throw new IllegalArgumentException("function was null");
		else {
			if (isPresent()) {
				return Optional.ofNullable(function.apply(get()));
			} else {
				return Optional.empty();
			}
		}
	}

	private final static Optional<?> emptyOptional = new Optional<>(null);

	public static <X> Optional<X> of(@NonNull X object) {
		if (object == null)
			throw new IllegalArgumentException("object was null");
		else
			return new Optional<>(object);
	}


	@NonNull
	public static <X> Optional<X> ofNullable(@Nullable X object) {
		if (object == null)
			return Optional.empty();
		else
			return new Optional<>(object);
	}

	/**
	 * Get an empty Optional
	 *
	 * @return An Optional, which is empty
	 */
	@SuppressWarnings("unchecked")
	@NonNull
	public static <X> Optional<X> empty() {
		return (Optional<X>) emptyOptional;
	}
}
