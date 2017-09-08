package io.freefair.util.function;

import android.support.annotation.Nullable;

/**
 * Represents an operation that accepts a single input argument and returns no result.
 * Unlike most other functional interfaces, Consumer is expected to operate via side-effects.
 *
 * @param <T> the type of the input to the operation
 * @see <a href ="http://docs.oracle.com/javase/8/docs/api/java/util/function/Consumer.html">http://docs.oracle.com/javase/8/docs/api/java/util/function/Consumer.html</a>
 */
public interface Consumer<T> {

    /**
     * Performs this operation on the given argument.
     *
     * @param value the input argument
     */
    void accept(@Nullable T value);
}
