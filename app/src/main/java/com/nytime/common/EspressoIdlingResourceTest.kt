package com.nytime.common

import androidx.test.espresso.IdlingResource


object EspressoIdlingResourceTest {
    var countingIdlingResource: SimpleCountingIdlingResourceTest? =
        SimpleCountingIdlingResourceTest()

    fun increment() {
        if (countingIdlingResource != null) countingIdlingResource!!.increment()
    }

    fun decrement() {
        if (countingIdlingResource != null) countingIdlingResource!!.decrement()
    }

    val idlingResource: IdlingResource?
        get() = countingIdlingResource
}
