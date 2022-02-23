package com.nytime.nytimes

import com.nytime.nytimes.client.request.ApiResponse
import com.nytime.nytimes.client.request.ErrorResponse


interface ApiResponseListener<T> {
    fun onApiSuccess(response: ApiResponse<T>)
    fun onApiFailure(errorResponse: ErrorResponse)
}