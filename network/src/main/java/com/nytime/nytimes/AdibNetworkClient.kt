package com.nytime.nytimes

import android.content.Context


class AdibNetworkClient private constructor(val networkConfig: NetworkConfig) {

    companion object {
        var instance: AdibNetworkClient? = null

        /**
         * Creates a singleton instance of network client. Should ideally be called from
         * you Application's onCreate()
         */
        // FIX-ME we need to find a better way to do this
        @Synchronized
        fun createInstance(networkConfig: NetworkConfig) {
            if (instance == null) {
                instance = AdibNetworkClient(networkConfig)
            } else {
                throw IllegalStateException("createInstance should be called only once")
            }
        }

        /**
         * Gets the current instance of Network client
         */
        @JvmName("getInstance1")
        fun getInstance(): AdibNetworkClient? {
            return if (instance == null) {
                throw IllegalStateException("getInstance can't be called before createInstance")
            } else {
                instance
            }
        }

        val context: Context
            get() = getInstance()!!.networkConfig.context
    }
}
