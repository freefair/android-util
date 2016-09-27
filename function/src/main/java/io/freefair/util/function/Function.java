package io.freefair.util.function;

import org.jetbrains.annotations.Nullable;

/**
 * Represents a function that accepts one argument and produces a result.
 *
 * @param <V> the type of the input to the function
 * @param <R> the type of the result of the function
 * @see <a href ="http://docs.oracle.com/javase/8/docs/api/java/util/function/Function.html">http://docs.oracle.com/javase/8/docs/api/java/util/function/Function.html</a>
 */
public interface Function<V, R> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    @Nullable
    R apply(@Nullable V value);
}
