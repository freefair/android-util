# android-util [![Build Status](https://travis-ci.org/freefair/android-util.svg?branch=master)](https://travis-ci.org/freefair/android-util)

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
    compile 'io.freefair:android-util:1.1.0'
    
    // Only some modules
    compile 'io.freefair.android-util:logging:1.1.0'
    compile 'io.freefair.android-util:function:1.1.0'
}
```
