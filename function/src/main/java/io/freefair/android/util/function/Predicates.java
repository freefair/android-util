package io.freefair.android.util.function;

import android.support.annotation.NonNull;

/**
 * Static methods for creating or obtaining {@link Predicate Predicates}
 */
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
	 * No instances of this class, please.
	 */
	private Predicates() {
	}

	/**
	 * @return A {@link Predicate} which is always true
	 */
	@SuppressWarnings("unchecked")
	@NonNull
	public static <T> Predicate<T> alwaysTrue() {
		return (Predicate<T>) TRUE;
	}

	/**
	 * @return A {@link Predicate} which is always false
	 */
	@SuppressWarnings("unchecked")
	@NonNull
	public static <T> Predicate<T> alwaysFalse() {
		return (Predicate<T>) FALSE;
	}

	@SuppressWarnings("unchecked")
	@NonNull
	public static <T> Predicate<T> notNull() {
		return (Predicate<T>) NOT_NULL;
	}

	@SuppressWarnings("unchecked")
	@NonNull
	public static <T> Predicate<T> isNull() {
		return (Predicate<T>) IS_NULL;
	}

	@NonNull
	public static <T> Predicate<T> isEqual(final Object object) {
		return new Predicate<T>() {
			@Override
			public boolean test(T value) {
				return value.equals(object);
			}
		};
	}

	@NonNull
	public static <T> Predicate<T> notEquals(final Object object) {
		return new Predicate<T>() {
			@Override
			public boolean test(T value) {
				return !value.equals(object);
			}
		};
	}

	@NonNull
	public static <T> Predicate<T> not(final Predicate<T> predicate) {
		return NotPredicate.of(predicate);
	}

	private static class NotPredicate<T> implements Predicate<T> {

		private Predicate<T> inner;

		private NotPredicate(Predicate<T> inner) {
			this.inner = inner;
		}

		@Override
		public boolean test(T value) {
			return !inner.test(value);
		}

		@NonNull
		public static <X> Predicate<X> of(Predicate<X> predicate) {
			if (predicate instanceof NotPredicate)
				return ((NotPredicate<X>) predicate).inner;
			else
				return new NotPredicate<>(predicate);
		}
	}
}
