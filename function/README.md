# util/function

Functional Interfaces like in java 8, but for Android:
- `Optional<T>`
- `Supplier<T>`
- `Function<V,R>`
- `Predicate<T>`

see also: http://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html

```gradle
repositories {
    // ...
    maven { url "https://jitpack.io" }
}

dependencies {
    compile 'io.freefair.android-util:function:1.0.0'
}
```
