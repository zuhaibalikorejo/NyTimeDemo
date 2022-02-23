package com.nytime.nytimes

import com.google.gson.JsonObject
import java.lang.reflect.Type


/**
 * Base for all network requests.
 *
 * @author zuhaib
 */
abstract class BaseRequest {
    abstract fun endpoint(): String?
    abstract fun requestMethod(): String?
    abstract fun postBody(): JsonObject?
    abstract fun responseType(): Type

    abstract fun offlineResponsePath(): String?
    val queryParams: Map<String, String>
        get() = HashMap()
}

