package com.nytime.nytimes

import com.nytime.nytimes.client.interceptor.RedirectResponseInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitProvider {
    private val TAG = RetrofitProvider::class.java.simpleName
    private const val TIMEOUT_DURATION = 120000 // 2 minutes
    private var okHttpClient: OkHttpClient? = null
    private var retrofit: Retrofit? = null
    val instance: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(AdibNetworkClient.instance!!.networkConfig.baseUrl)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(secureOkHttpClient)
                    .build()
            }
            retrofit = retrofit!!.newBuilder().callFactory(updatedClient).build()
            return retrofit
        }
    private val updatedClient: OkHttpClient
        private get() = okHttpClient!!.newBuilder().build()
    private val secureOkHttpClient: OkHttpClient
        private get() {
            okHttpClient = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(logLevel))
                .addNetworkInterceptor(RedirectResponseInterceptor())
                .readTimeout(TIMEOUT_DURATION.toLong(), TimeUnit.MILLISECONDS)
                .connectTimeout(TIMEOUT_DURATION.toLong(), TimeUnit.MILLISECONDS)
                .build()
            return okHttpClient!!
        }
    private val logLevel: HttpLoggingInterceptor.Level
        private get() = HttpLoggingInterceptor.Level.NONE
}