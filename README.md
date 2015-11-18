# android-util
[![Release](https://img.shields.io/github/release/freefair/android-util.svg?label=JitPack)](https://jitpack.io/#io.freefair/android-util)

Android Utility classes

- [logging](logging) -> Simple logging
- [preference](preference) -> AppCompatPreferenceActivity
- [function](function) -> Functional Interfaces

## How to include
```gradle
repositories {
    // ...
    maven { url "https://jitpack.io" }
}

dependencies {
    // All at once
    compile 'io.freefair:android-util:1.0.1'
    
    // Only some modules
    compile 'io.freefair.android-util:logging:1.0.1'
    compile 'io.freefair.android-util:function:1.0.1'
}
```
