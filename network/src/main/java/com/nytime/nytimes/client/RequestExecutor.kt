package com.nytime.nytimes.client

import com.nytime.nytimes.ApiResponseListener
import com.nytime.nytimes.BaseRequest


interface RequestExecutor {
    fun <T> execute(request: BaseRequest, listener: ApiResponseListener<T>)
}