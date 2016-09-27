package io.freefair.util.function;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

/**
 * Static methods for creating or obtaining {@link Predicate Predicates}
 */
@SuppressWarnings({"unused", "WeakerAccess"})
@NoArgsConstructor(access = PRIVATE)
public final class Predicates {

    private static final Predicate<?> TRUE = new Predicate<Object>() {
        @Override
        public boolean test(Object value) {
            return true;
        }
    };

    private static final Predicate<?> FALSE = new Predicate<Object>() {
        @Override
        public boolean test(Object value) {
            return false;
        }
    };

    private static final Predicate<?> NOT_NULL = new Predicate<Object>() {
        @Override
        public boolean test(Object value) {
            return value != null;
        }
    };

    private static final Predicate<?> IS_NULL = new Predicate<Object>() {
        @Override
        public boolean test(Object value) {
            return value == null;
        }
    };

    /**
     * @return A {@link Predicate} which is always true
     */
    @SuppressWarnings("unchecked")
    @NotNull
    public static <T> Predicate<T> alwaysTrue() {
        return (Predicate<T>) TRUE;
    }

    /**
     * @return A {@link Predicate} which is always false
     */
    @SuppressWarnings("unchecked")
    @NotNull
    public static <T> Predicate<T> alwaysFalse() {
        return (Predicate<T>) FALSE;
    }

    /**
     * @return A {@link Predicate} which is true, if its input is not null
     */
    @SuppressWarnings("unchecked")
    @NotNull
    public static <T> Predicate<T> notNull() {
        return (Predicate<T>) NOT_NULL;
    }

    /**
     * @return A {@link Predicate} which is true, if its input is null
     */
    @SuppressWarnings("unchecked")
    @NotNull
    public static <T> Predicate<T> isNull() {
        return (Predicate<T>) IS_NULL;
    }

    /**
     * Returns a predicate that tests if two arguments are equal according to {@link Objects#equals(Object, Object)}.
     *
     * @param object the object reference with which to compare for equality, which may be null
     * @param <T>    the type of arguments to the predicate
     * @return a predicate that tests if two arguments are equal according to {@link Objects#equals(Object, Object)}
     */
    @NotNull
    public static <T> Predicate<T> isEqual(@Nullable final Object object) {
        return new Predicate<T>() {
            @Override
            public boolean test(T value) {
                return (value == null) ? (object == null) : value.equals(object);
            }
        };
    }

    /**
     * Returns a predicate that tests if two arguments are not equal according to {@link Objects#equals(Object, Object)}.
     *
     * @param object the object reference with which to compare for inequality, which may be null
     * @param <T>    the type of arguments to the predicate
     * @return a predicate that tests if two arguments are not equal according to {@link Objects#equals(Object, Object)}
     */
    @NotNull
    public static <T> Predicate<T> notEquals(@Nullable final Object object) {
        Predicate<T> equal = isEqual(object);
        return not(equal);
    }

    /**
     * Returns a predicate that represents the logical negation of the given predicate.
     *
     * @param predicate the predicate to negate
     * @param <T>
     * @return a predicate that represents the logical negation of the given predicate
     */
    @NotNull
    public static <T> Predicate<T> not(final Predicate<T> predicate) {
        return NotPredicate.of(predicate);
    }

    /**
     * Returns a composed predicate that represents a short-circuiting logical AND of two predicates.
     * When evaluating the composed predicate, if the first predicate is false, then the second predicate is not evaluated.
     * <p/>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller;
     * if evaluation of the first predicate throws an exception, the second predicate will not be evaluated.
     *
     * @param first  The first predicate
     * @param second The second predicate
     * @param <T>    common super type of both predicates
     * @return a composed predicate that represents the short-circuiting logical AND of the two predicates
     */
    @NotNull
    public static <T> Predicate<T> and(final Predicate<? super T> first, final Predicate<? super T> second) {
        return new Predicate<T>() {
            @Override
            public boolean test(@Nullable T value) {
                return first.test(value) && second.test(value);
            }
        };
    }

    /**
     * Returns a composed predicate that represents a short-circuiting logical OR of two predicates.
     * When evaluating the composed predicate, if the first predicate is true, then the second predicate is not evaluated.
     * <p/>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller;
     * if evaluation of the first predicate throws an exception, the second predicate will not be evaluated.
     *
     * @param a   The first predicate
     * @param b   The second predicate
     * @param <T> common super type of both predicates
     * @return a composed predicate that represents the short-circuiting logical OR of the two predicates
     */
    @NotNull
    public static <T> Predicate<T> or(final Predicate<? super T> a, final Predicate<? super T> b) {
        return new Predicate<T>() {
            @Override
            public boolean test(@Nullable T value) {
                return a.test(value) || b.test(value);
            }
        };
    }

    /**
     * This class is not anonymous in order to prevent not(not()) chains
     */
    private static class NotPredicate<T> implements Predicate<T> {

        private final Predicate<T> inner;

        private NotPredicate(Predicate<T> inner) {
            this.inner = inner;
        }

        @Override
        public boolean test(T value) {
            return !inner.test(value);
        }

        @NotNull
        public static <X> Predicate<X> of(Predicate<X> predicate) {
            if (predicate instanceof NotPredicate)
                return ((NotPredicate<X>) predicate).inner;
            else
                return new NotPredicate<>(predicate);
        }
    }
}
