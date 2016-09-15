package io.freefair.util.function;


import org.jetbrains.annotations.NotNull;

/**
 * Static functions for creating Consumers
 */
public class Consumers {

    private Consumers() {
    }

    private static final Consumer<?> NOTHING = new Consumer<Object>() {
        @Override
        public void accept(Object value) {
        }
    };

    /**
     * Get an {@link Consumer}, which does absolutely nothing.
     *
     * @param <T> the type of the input to the consumer
     * @return a {@link Consumer} which does nothing
     */
    @SuppressWarnings("unchecked")
    @NotNull
    public static <T> Consumer<T> nothing(){
        return (Consumer<T>) NOTHING;
    }

    /**
     * Returns a composed Consumer that performs, in sequence, the first operation followed by the second operation.
     * If performing either operation throws an exception, it is relayed to the caller of the composed operation.
     * If performing the first operation throws an exception, the second operation will not be performed.
     *
     * @param first  the operation to perform first
     * @param second the operation to perform second
     * @param <T>    the type of the input to the composed operation
     * @return a composed Consumer that performs in sequence the first operation followed by the second operation
     * @throws NullPointerException if first or second is null;
     */
    @NotNull
    public static <T> Consumer<T> chain(final Consumer<? super T> first, final Consumer<? super T> second) {
        return new Consumer<T>() {
            @Override
            public void accept(T value) {
                first.accept(value);
                second.accept(value);
            }
        };
    }

    /**
     * Create a {@link Consumer} which represents the given predicate.
     * <p/>
     * The return value of {@link Predicate#test(Object)} is ignored.
     *
     * @param predicate the predicate to convert.
     * @param <T>       the input type of the predicate and of the new consumer
     * @return a Consumer representation of the given predicate
     */
    @NotNull
    public static <T> Consumer<T> ofPredicate(final Predicate<T> predicate) {
        return new Consumer<T>() {
            @Override
            public void accept(T value) {
                predicate.test(value);
            }
        };
    }

    /**
     * Create a {@link Consumer} which represents the given function.
     * <p/>
     * The return value of {@link Function#apply(Object)} is ignored.
     *
     * @param function the function to convert.
     * @param <T>      the input type of the function and of the new consumer
     * @return a Consumer representation of the given function
     */
    @NotNull
    public static <T> Consumer<T> ofFunction(final Function<T, ?> function) {
        return new Consumer<T>() {
            @Override
            public void accept(T value) {
                function.apply(value);
            }
        };
    }
}
