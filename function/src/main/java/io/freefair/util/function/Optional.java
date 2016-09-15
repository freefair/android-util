package io.freefair.util.function;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.NoSuchElementException;

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

	@Nullable
	private final T object;

	/**
	 * @see #of(Object)
	 * @see #ofNullable(Object)
	 */
	private Optional(@Nullable T obj) {
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
	 * If a value is present in this Optional, returns the value, otherwise throws NoSuchElementException.
	 * @return the non-null value held by this Optional
	 * @throws NoSuchElementException if there is no value present
	 */
	@NotNull
	public T get() throws NoSuchElementException {
		if (object != null)
			return object;
		else {
			throw new NoSuchElementException("This optional is empty");
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
	 * Return the value if present, otherwise invoke other and return the result of that invocation.
	 *
	 * @param other a Supplier whose result is returned if no value is present
	 * @return the value if present otherwise the result of other.get()
	 * @throws NullPointerException if value is not present and other is null
	 */
	public T orElseGet(Supplier<? extends T> other) {
		return isPresent() ? object : other.get();
	}

	/**
	 * Return the contained value, if present, otherwise throw an exception to be created by the provided supplier.
	 *
	 * @param exceptionSupplier The supplier which will return the exception to be thrown
	 * @param <X>               Type of the exception to be thrown
	 * @return the present value
	 * @throws X                    if there is no value present
	 * @throws NullPointerException if no value is present and exceptionSupplier is null
	 */
	@NotNull
	public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
		if (isPresent()) {
			return object;
		} else {
			throw exceptionSupplier.get();
		}
	}

	/**
	 * If a value is present, and the value matches the given predicate,
	 * return an Optional describing the value, otherwise return an empty Optional.
	 *
	 * @param predicate a predicate to apply to the value, if present
	 * @return an Optional describing the value of this Optional if a value is present and the value matches the given predicate,
	 * otherwise an empty Optional
	 */
	public Optional<T> filter(Predicate<? super T> predicate) {
		if (isPresent() && predicate.test(get())) {
			return Optional.of(get());
		} else {
			return empty();
		}
	}

	/**
	 * If a value is present, apply the provided mapping function to it,
	 * and if the result is non-null, return an Optional describing the result.
	 * Otherwise return an empty Optional.
	 *
	 * @param function a mapping function to apply to the value, if present
	 * @param <V>      The type of the result of the mapping function
	 * @return an Optional describing the result of applying a mapping function to the value of this Optional,
	 * if a value is present, otherwise an empty Optional
	 * @throws IllegalArgumentException if the mapping function is null
	 */
	@NotNull
	public <V> Optional<V> map(Function<? super T, ? extends V> function) throws IllegalArgumentException {
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

	/**
	 * If a value is present, apply the provided Optional-bearing mapping function to it,
	 * return that result, otherwise return an empty Optional.
	 * This method is similar to map(Function), but the provided mapper is one whose result is already an Optional,
	 * and if invoked, flatMap does not wrap it with an additional Optional.
	 *
	 * @param function a mapping function to apply to the value, if present the mapping function
	 * @param <U>      The type parameter to the Optional returned by
	 * @return the result of applying an Optional-bearing mapping function to the value of this Optional, if a value is present, otherwise an empty Optional
	 */
	public <U> Optional<U> flatMap(Function<? super T, Optional<U>> function) {
		Optional<Optional<U>> optionalOptional = map(function);
		if (optionalOptional.isPresent()) {
			return optionalOptional.get();
		} else {
			return empty();
		}
	}

	/**
	 * Indicates whether some other object is "equal to" this Optional. The other object is considered equal if:
	 * <p/>
	 * <ul>
	 * <li>it is also an Optional and;</li>
	 * <li>both instances have no value present or;</li>
	 * <li>the present values are "equal to" each other via equals().</li>
	 * </ul>
	 *
	 * @param o an object to be tested for equality
	 * @return {@code true} if the other object is "equal to" this object otherwise false
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null || !(o instanceof Optional))
			return false;

		if (this == o)
			return true;

		Optional other = (Optional) o;

		if (!this.isPresent() && !other.isPresent()) {
			return true;
		} else if (this.isPresent() && other.isPresent()) {
			return this.get().equals(other.get());
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return object != null ? object.hashCode() : 0;
	}

	private final static Optional<?> EMPTY_OPTIONAL = new Optional<>(null);

	public static <X> Optional<X> of(X object) {
		if (object == null)
			throw new IllegalArgumentException("object was null");
		else
			return new Optional<>(object);
	}


	@NotNull
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
	@NotNull
	public static <X> Optional<X> empty() {
		return (Optional<X>) EMPTY_OPTIONAL;
	}
}
