package io.freefair.android.util.logging;

import android.support.annotation.NonNull;
import android.util.Log;

/**
 * A simple {@link Logger} which logs against {@link Log} with the given tag.
 */
@SuppressWarnings("unused")
public class AndroidLogger implements Logger {

	/**
	 * The tag used vor {@link Log}
	 */
	private String tag;

	/**
	 * @param tag The tag to use for logging
	 * @throws IllegalArgumentException If the tag is null
	 */
	private AndroidLogger(String tag) throws IllegalArgumentException {
		if (tag == null) {
			throw new IllegalArgumentException("Tag should not be null");
		}
		if (tag.length() <= 23) {
			this.tag = tag;
		} else {
			this.tag = tag.substring(tag.length() - 23);
		}
	}

	@NonNull
	public String getTag() {
		return tag;
	}

	@Override
	public void verbose(String text) {
		Log.v(getTag(), text);
	}

	@Override
	public void verbose(String text, Throwable throwable) {
		Log.v(getTag(), text, throwable);
	}

	@Override
	public void info(String text) {
		Log.i(getTag(), text);
	}

	@Override
	public void info(String text, Throwable throwable) {
		Log.i(getTag(), text, throwable);
	}

	@Override
	public void warn(String text) {
		Log.w(getTag(), text);
	}

	@Override
	public void warn(String text, Throwable throwable) {
		Log.w(getTag(), text, throwable);
	}

	@Override
	public void debug(String text) {
		Log.d(getTag(), text);
	}

	@Override
	public void debug(String text, Throwable throwable) {
		Log.d(getTag(), text, throwable);
	}

	@Override
	public void error(String text) {
		Log.e(getTag(), text);
	}

	@Override
	public void error(String text, Throwable throwable) {
		Log.e(getTag(), text, throwable);
	}

	/**
	 * Create an {@link AndroidLogger} which uses the given tag.
	 *
	 * @param tag The tag to use
	 * @return A new {@link AndroidLogger}
	 * @see Log
	 */
	@NonNull
	public static AndroidLogger withTag(String tag) {
		return new AndroidLogger(tag);
	}

	/**
	 * Create an {@link AndroidLogger} which uses the {@link Class#getSimpleName() simple name} of the given class as tag
	 *
	 * @param clazz The class to use as tag.
	 * @return A new {@link AndroidLogger}
	 * @see #withTag(String)
	 */
	@NonNull
	public static Logger forClass(Class<?> clazz) {
		return withTag(clazz.getSimpleName());
	}

	/**
	 * Create an {@link AndroidLogger} which uses the {@link Class#getSimpleName() simple name}
	 * of the {@link Object#getClass() class} of the given object
	 *
	 * @param object The object to use as tag.
	 * @return A new {@link AndroidLogger}
	 * @see #forClass(Class)
	 */
	@NonNull
	public static Logger forObject(Object object) {
		return forClass(object.getClass());
	}
}
