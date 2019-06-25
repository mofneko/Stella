<img src="./art/Stella.jpg" alt="Stella" style="width:300px;"/>

=============================================

[![Release](https://jitpack.io/v/mofneko/Stella.svg)](https://jitpack.io/#mofneko/Stella)

Stella is speedhack tracking agent for Android.

# How to use

##### Java and Kotlin

Users of your library will need add the jitpack.io repository:

```gradle
allprojects {
 repositories {
    jcenter()
    maven { url "https://jitpack.io" }
 }
}
```

and:

```gradle
dependencies {
    compile 'com.github.mofneko:Stella:0.1.1'
}
```

Step1. Handling SpeedHackDetectDelegate

```kotlin
            val detectCallback = object : StellaCallback() {
                override fun onDetect() {
                    // Speed Hack Detection.
                }
            }
```

Step2. Detect

```kotlin
        var stella = Stella()
        stella.initialize(
         3000, // Frequency of detection(ms). Set a value more than 1second.
         detectCallback)
        button.setOnClickListener {
            stella.start()
        }
```

NOTE: To improve performance, stop detection when it is not necessary.

```kotlin
            stella.start()
```

##### C# (Unity)
Create a folder with the structure Assets/Plugins/Android and put [*.aar](https://github.com/mofneko/Stella/blob/master/aar/) in the Android folder.

and fact Delegate.

```C# (Unity)
　　public class DetectListener : AndroidJavaProxy
    {
        public DetectListener()
            : base("com.nekolaboratory.Stella.StellaCallback")
        {
        }
        void onDetect() {
            // Speed Hack Detection.
        }
    }
```

and execute Detect.

```C# (Unity)
    void Detect()
    {
        // Step1. Instantiate
        using (AndroidJavaObject Stella = new AndroidJavaObject("com.nekolaboratory.Stella.Stella"))
        {
        // Step2. Detect
        Stella.Call("initialize",
         3000, // Frequency of detection(ms). Set a value more than 1second.
         new DetectListener());
        Stella.Call("start");
        }
    }
```

To improve performance, stop detection when it is not necessary.

```C# (Unity)
        // The stop function is an instance method.
        Stella.Call("stop");
```

# Development

```
$ git clone git@github.com:mofneko/Stella.git
$ cd Stella
$ ./gradlew assembleRelease
```

# License

```
MIT License

Copyright (c) 2019 Yusuke Arakawa

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
