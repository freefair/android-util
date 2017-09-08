package io.freefair.util.function;

import android.support.annotation.Nullable;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

/**
 * Static methods for creating {@link Function functions}
 */
@SuppressWarnings({"unused", "WeakerAccess"})
@NoArgsConstructor(access = PRIVATE)
public class Functions {

    private static final Function<?, ?> IDENTITY = new Function() {
        @Nullable
        @Override
        public Object apply(@Nullable Object value) {
            return value;
        }
    };

    @SuppressWarnings("unchecked")
    public static <T> Function<T, T> identity() {
        return (Function<T, T>) IDENTITY;
    }

    /**
     * Returns a composed function that first applies the first function to its input, and then applies the second function to the result.
     * If evaluation of either function throws an exception, it is relayed to the caller of the composed function.
     *
     * @param first  the function to apply first
     * @param second the function to apply after the first function is applied
     * @param <A>    The input type of the first, and of the composed function
     * @param <B>    The result type of the first function, and the input type of the second function
     * @param <C>    The result type of the second function, and of the composed function
     * @return a composed function that first applies the first function and then applies the second function
     * @throws NullPointerException if one of the given functions is null
     */
    public static <A, B, C> Function<A, C> chain(final Function<? super A, ? extends B> first,
                                                 final Function<? super B, ? extends C> second) {
        return new Function<A, C>() {
            @Nullable
            @Override
            public C apply(@Nullable A value) {
                return second.apply(first.apply(value));
            }
        };
    }

    /**
     * Create a {@link Function} which represents the given predicate.
     *
     * @param predicate the predicate to convert.
     * @param <T>       the input type of the predicate and of the new function
     * @return a Function representation of the given predicate
     */
    public static <T> Function<T, Boolean> ofPredicate(final Predicate<T> predicate) {
        return new Function<T, Boolean>() {
            @Nullable
            @Override
            public Boolean apply(@Nullable T value) {
                return predicate.test(value);
            }
        };
    }
}
