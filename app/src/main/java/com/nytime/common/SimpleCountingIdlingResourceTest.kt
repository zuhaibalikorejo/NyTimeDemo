package com.nytime.common

import androidx.test.espresso.IdlingResource
import java.util.concurrent.atomic.AtomicInteger


class SimpleCountingIdlingResourceTest : IdlingResource {
    private val counter = AtomicInteger(0)

    // written from main thread, read from any thread.
    private var resourceCallback: IdlingResource.ResourceCallback? = null
    override fun getName(): String {
        return ""
    }

    override fun isIdleNow(): Boolean {
        return counter.get() == 0
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback) {
        resourceCallback = callback
    }

    /**
     * Increments the count of in-flight transactions to the resource being monitored.
     */
    fun increment() {
        counter.getAndIncrement()
    }

    /**
     * Decrements the count of in-flight transactions to the resource being monitored.
     * If this operation results in the counter falling below 0 - an exception is raised.
     *
     * @throws IllegalStateException if the counter is below 0.
     */
    fun decrement() {
        val counterVal = counter.decrementAndGet()
        if (counterVal == 0) {
            // we've gone from non-zero to zero. That means we're idle now! Tell espresso.
            if (resourceCallback != null) resourceCallback!!.onTransitionToIdle()
        } else require(counterVal >= 0) { "Counter has been corrupted!" }
    }
}
