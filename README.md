# android-util
Android Utility classes

- [logging](logging) -> Simple logging
- [preference](preference) -> AppCompatPreferenceActivity
- [function](function) -> Functional Interfaces

```gradle
repositories {
    // ...
    maven { url "https://jitpack.io" }
}

dependencies {
    // All at once
    compile 'io.freefair:android-util:1.0.0'
    
    // Only some modules
    compile 'io.freefair.android-util:logging:1.0.0'
    compile 'io.freefair.android-util:function:1.0.0'
}
```
