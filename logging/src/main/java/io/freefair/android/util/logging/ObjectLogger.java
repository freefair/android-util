package io.freefair.android.util.logging;

/**
 * Created by larsgrefer on 06.11.15.
 */
public class ObjectLogger extends ClassLogger {

	ObjectLogger(Object tag) {
		super(tag.getClass());
	}
}
