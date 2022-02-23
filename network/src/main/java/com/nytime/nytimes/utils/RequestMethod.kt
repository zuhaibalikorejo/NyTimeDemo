package com.nytime.nytimes.utils

import androidx.annotation.StringDef
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

object RequestMethod {
    const val POST_FORM_URL_ENCODED = "POST_FORM_URL_ENCODED"
    const val GET = "GET"
    const val POST = "POST"
    const val PATCH = "PATCH"
    const val PUT = "PUT"
    const val DELETE = "DELETE"

    @Retention(RetentionPolicy.SOURCE)
    @StringDef(
        POST_FORM_URL_ENCODED, GET, POST, PATCH, PUT, DELETE
    )
    annotation class RequestMethods
}