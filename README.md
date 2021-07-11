# Fallen Meteors
This app shows all the data that NASA has of fallen meteors since the year 1900.

In this app, I decided to put corrotinas instead of RxJava, as Kotlin corrotinas provide much more flexibility than simple reactive programming, but I know that RxJava is still a great (and probably perfect) tool whenever we need to process real flows, where it doesn't is the case with this app, and Kotlin coroutines can interact with RxJava when needed. 


## Dependencies implementation
I used in this app the following libraries:

For the ViewModel and LiveData
- lifecycle-viewmodel-ktx
- lifecycle-livedata-ktx

For Moshi
- moshi
- moshi-kotlin

For Retrofit and Moshi Converter
- retrofit
- retrofit converter-moshi

For Coroutines
- kotlinx-coroutines-core
- kotlinx-coroutines-android

For RecyclerView Swipe
- swiperefreshlayout

For Maps
- play-services-maps
- play-services-location

For the Feature module Support
- navigation-dynamic-features-fragment


## Licence
Copyright 2021 Daniel Freitas Vilha
```
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
of the Software, and to permit persons to whom the Software is furnished to do so,
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
