# util/logging

A simple logging interface and implementation for Android

```java
Logger log1 = AndroidLogger.withTag("Hello World");
Logger log2 = AndroidLogger.forObject(this);
Logger log3 = AndroidLogger.forClass(Object.class);
```

```gradle
repositories {
    // ...
    maven { url "https://jitpack.io" }
}

dependencies {
    compile 'io.freefair.android-util:logging:1.0.1'
}
```
