package io.freefair.util.function;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

/**
 * Static methods for creating {@link Supplier Suppliers}
 */
@SuppressWarnings({"unused", "WeakerAccess"})
@NoArgsConstructor(access = PRIVATE)
public final class Suppliers {

    /**
     * Create a {@link Supplier} which supplies the given object
     *
     * @param object The object to supply
     * @return A {@link Supplier} supplying the given object
     */
    @NonNull
    public static <T> Supplier<T> of(final T object) {
        return new Supplier<T>() {
            @Override
            public T get() {
                return object;
            }
        };
    }

    /**
     * Create a {@link Supplier} which caches the value returned by the given supplier.
     *
     * @param supplier The supplier to cache. {@link Supplier#get() get()} will only called once on this.
     */
    @NonNull
    public static <T> Supplier<T> cache(final Supplier<T> supplier) {
        if (supplier instanceof SupplierCachingSupplier)
            return supplier;
        else
            return new SupplierCachingSupplier<>(supplier);
    }

    @RequiredArgsConstructor
    private static class SupplierCachingSupplier<T> extends CachingSupplier<T> {

        private final Supplier<T> supplier;

        @Nullable
        @Override
        protected T create() {
            return supplier.get();
        }
    }

    public abstract static class CachingSupplier<T> implements Supplier<T> {

        /**
         * The cache
         */
        @Nullable
        private T object;

        /**
         * If the cache is filled
         */
        private boolean created = false;

        @Override
        @Nullable
        public T get() {
            if (!created) {
                object = create();
                created = true;
            }
            return object;
        }

        /**
         * Create the result that should be cached.
         * This method is called only once.
         *
         * @return The result, which will be cached.
         */
        @Nullable
        protected abstract T create();
    }
}
