package io.freefair.android.util.function;

/**
 * Represents a predicate (boolean-valued function) of one argument.
 *
 * @param <T> the type of the input to the predicate
 * @see <a href="http://docs.oracle.com/javase/8/docs/api/java/util/function/Predicate.html">http://docs.oracle.com/javase/8/docs/api/java/util/function/Predicate.html</a>
 */
public interface Predicate<T> {

	/**
	 * Evaluates this predicate on the given argument.
	 *
	 * @param value the input argument
	 * @return true if the input argument matches the predicate, otherwise false
	 */
	boolean test(T value);
}
