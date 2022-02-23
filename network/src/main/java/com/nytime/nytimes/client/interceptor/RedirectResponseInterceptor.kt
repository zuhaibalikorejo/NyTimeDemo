package com.nytime.nytimes.client.interceptor



import okhttp3.*
import java.io.IOException


/**
 * Activity Interceptor class for application's dashboard
 * <p>
 * @author zuhaib
 * Email: zuhaib.ahmed@adib.com
 * Company: zuhaib
 */
class RedirectResponseInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        return response
    }

}
