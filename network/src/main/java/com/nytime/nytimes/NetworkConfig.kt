package com.nytime.nytimes

import android.content.Context


class NetworkConfig(builder: Builder) {
    val baseUrl: String
    val context: Context

    class Builder( val context: Context,  val baseUrl: String) {
        fun build(): NetworkConfig {
            return NetworkConfig(this)
        }
    }

    init {
        context = builder.context
        baseUrl = builder.baseUrl
    }
}
