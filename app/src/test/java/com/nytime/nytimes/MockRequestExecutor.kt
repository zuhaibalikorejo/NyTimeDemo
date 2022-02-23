package com.nytime.nytimes

import android.util.Log
import com.google.gson.Gson
import com.nytime.nytimes.client.RequestExecutor
import com.nytime.nytimes.client.request.ApiResponse
import com.nytime.nytimes.client.request.ErrorResponse
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.io.StringWriter

class MockRequestExecutor(private var simulateFailure: Boolean) : RequestExecutor {


    override fun <T> execute(request: BaseRequest, listener: ApiResponseListener<T>) {
        val localData = getJsonFromTestPath(request.offlineResponsePath()!!)
        if (!simulateFailure) {
            onSuccess(request, listener, localData)
        } else {
            listener.onApiFailure(ErrorResponse())
        }
    }

    private fun getJsonFromTestPath(path: String): String {
        var result = ""
        var n: Int
        val buffer = CharArray(1024 * 4)
        var inputStream: InputStream? = null
        try {
            inputStream = this.javaClass.classLoader?.getResourceAsStream(path)
            if (null != inputStream) {
                try {
                    InputStreamReader(inputStream, "UTF8").use { reader ->
                        val writer = StringWriter()
                        while (-1 != reader.read(buffer).also { n = it }) writer.write(buffer, 0, n)
                        result = writer.toString()
                    }
                } catch (e: IOException) {
                    Log.v("TAG", e.message!!)
                }
            }
        } finally {
            try {
                inputStream?.close()
            } catch (e: IOException) {
                Log.v("TAG", e.message!!)
            }
        }
        return result
    }

    private fun <T> onSuccess(
        request: BaseRequest,
        listener: ApiResponseListener<T>,
        localData: String
    ) {
        val apiResponse = ApiResponse<T>()
        apiResponse.data = Gson().fromJson(localData, request.responseType())
        apiResponse.responseCode = 200
        listener.onApiSuccess(apiResponse)
    }
}