package com.nytime.nytimes.client

import android.content.Context
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader


/**
 * Activity ViewModel class for application's dashboard
 *
 *
 * @author zuhaib
 * Email: zuhaib.ahmed@adib.com
 * Company: zuhaib
 */
object Util {
    var ERROR_CODE_NETWORK_ERROR = "300"
    fun readFileFromAssets(ctx: Context, fileName: String?): String {
        val builder = StringBuilder()
        val `is`: InputStream
        try {
            `is` = ctx.assets.open(fileName!!)
            val bufferedReader = BufferedReader(InputStreamReader(`is`, "UTF-8"))
            var str: String?
            while (bufferedReader.readLine().also { str = it } != null) {
                builder.append(str)
            }
            bufferedReader.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return builder.toString()
    }
}
