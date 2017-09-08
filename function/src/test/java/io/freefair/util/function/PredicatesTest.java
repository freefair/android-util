package io.freefair.util.function;

import org.junit.Test;

import java.util.Arrays;

import static io.freefair.util.function.Predicates.not;
import static org.assertj.core.api.Assertions.assertThat;

public class PredicatesTest {

    @Test
    public void testNotEquals() throws Exception {
        Predicate<Object> predicate = Predicates.notEquals("foo");

        assertThat(predicate.test("foo")).isFalse();
        assertThat(predicate.test("bar")).isTrue();
        assertThat(predicate.test(null)).isTrue();
    }

    @Test
    public void testAlwaysTrue() {
        Predicate<Object> alwaysTrue = Predicates.alwaysTrue();

        assertThat(alwaysTrue.test(null)).isTrue();
        assertThat(alwaysTrue.test("Hallo")).isTrue();
        assertThat(alwaysTrue.test(55l)).isTrue();
    }

    @Test
    public void testAlwaysFalse() {
        Predicate<Object> alwaysFalse = Predicates.alwaysFalse();

        assertThat(alwaysFalse.test(null)).isFalse();
        assertThat(alwaysFalse.test("FooBar")).isFalse();
        assertThat(alwaysFalse.test(66d)).isFalse();
    }

    @Test
    public void testNotNull() throws Exception {
        Predicate<Object> notNull = Predicates.notNull();

        assertThat(notNull.test("Hallo")).isTrue();
        assertThat(notNull.test(null)).isFalse();
    }

    @Test
    public void testIsNull() throws Exception {
        Predicate<Object> isNull = Predicates.isNull();

        assertThat(isNull.test(null)).isTrue();
        assertThat(isNull.test("test")).isFalse();
    }

    @Test
    public void testNotNot() {
        for (Predicate<Object> predicate : Arrays.asList(Predicates.alwaysFalse(), Predicates.alwaysTrue())) {
            assertThat(not(not(predicate))).isSameAs(predicate);
        }
    }
}