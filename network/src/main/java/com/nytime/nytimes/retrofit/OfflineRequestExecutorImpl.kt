package com.nytime.nytimes.retrofit


import android.os.Handler
import android.os.SystemClock
import com.nytime.network.R
import com.nytime.nytimes.AdibNetworkClient
import com.nytime.nytimes.ApiResponseListener

import com.nytime.nytimes.BaseRequest

import com.nytime.nytimes.client.RequestExecutor
import com.nytime.nytimes.client.Util
import com.nytime.nytimes.client.request.ErrorResponse


class OfflineRequestExecutorImpl : RequestExecutor {
    private val simulateError = false
    override fun <T> execute(request: BaseRequest, listener: ApiResponseListener<T>) {
        val localData =
            Util.readFileFromAssets(AdibNetworkClient.context, request.offlineResponsePath())
        val thread = Thread {
            SystemClock.sleep(1000)
            Handler(AdibNetworkClient.context.mainLooper)
                .post {
                    if (simulateError) {
                        val errorResponse = ErrorResponse()
                        errorResponse.setErrorTitle(
                            AdibNetworkClient.context.getString(R.string.error)
                        )
                        listener.onApiFailure(errorResponse)
                    } else onNoErrorInResponse(request, listener, localData)
                }
        }
        thread.start()
    }

    private fun <T> onNoErrorInResponse(
        request: BaseRequest,
        listener: ApiResponseListener<T>,
        localData: String
    ) {
    }
}
