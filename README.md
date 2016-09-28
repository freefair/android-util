# android-util [![Build Status](https://travis-ci.org/freefair/android-util.svg?branch=master)](https://travis-ci.org/freefair/android-util)

Android Utility classes

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
    compile 'io.freefair:android-util:<version>'
    
    // Only some modules
    compile 'io.freefair.android-util:function:<version>'
}
```
