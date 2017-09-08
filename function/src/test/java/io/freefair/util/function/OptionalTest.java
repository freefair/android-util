package io.freefair.util.function;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;

public class OptionalTest {

    private Optional empty;
    private Optional notEmpty;
    private Optional notEmpty2;

    @Before
    public void setUp() throws Exception {
        empty = Optional.empty();
        notEmpty = Optional.of("Hallo");
        notEmpty2 = Optional.of(2);
    }

    @Test
    public void testIsPresent() throws Exception {
        assertThat(empty.isPresent()).isFalse();
        assertThat(notEmpty.isPresent()).isTrue();
        assertThat(notEmpty2.isPresent()).isTrue();
    }

    @Test
    public void testGet() throws Exception {
        assertThat(notEmpty.get()).isNotNull();
        assertThat(notEmpty2.get()).isNotNull();
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetOnEmtpy() throws Exception {
        empty.get();
    }

    @Test
    public void testOrNull() throws Exception {
        assertThat(empty.orNull()).isNull();
        assertThat(notEmpty.orNull()).isNotNull();
        assertThat(notEmpty2.orNull()).isNotNull();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testOrElse() throws Exception {
        Object _else = "B";

        assertThat(empty.orElse(_else)).isEqualTo(_else);
        assertThat(notEmpty.orElse(_else)).isNotEqualTo(_else);
        assertThat(notEmpty2.orElse(_else)).isNotEqualTo(_else);
    }

    @Test
    public void testOrElseGet() throws Exception {

    }

    @Test
    public void testOrElseThrow() throws Exception {

    }

    @SuppressWarnings("unchecked")
    @Test
    public void testFilter() throws Exception {
        assertThat(empty.filter(Predicates.alwaysTrue()).isPresent()).isFalse();
        assertThat(notEmpty.filter(Predicates.alwaysTrue()).isPresent()).isTrue();
        assertThat(notEmpty.filter(Predicates.alwaysFalse()).isPresent()).isFalse();
    }

    @Test
    public void testMap() throws Exception {

    }

    @Test
    public void testFlatMap() throws Exception {

    }

    @Test
    public void testEquals() throws Exception {
        assertThat(empty).isEqualTo(Optional.empty());
        assertThat(notEmpty).isNotEqualTo(notEmpty2);
        assertThat(notEmpty).isNotEqualTo(empty);
    }

    @Test
    public void testHashCode() throws Exception {
        assertThat(empty.hashCode()).isEqualTo(0);
        assertThat(notEmpty.get().hashCode()).isEqualTo(notEmpty.hashCode());
    }

    @Test
    public void testEmpty() throws Exception {
        assertThat(Optional.empty().isPresent()).isFalse();
    }
}