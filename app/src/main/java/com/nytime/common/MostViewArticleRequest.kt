
package com.nytime.common


import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.nytime.nytimes.utils.RequestMethod
import com.nytime.request.NyTimeBaseRequest
import com.nytime.ui.model.MostViewArticleModel

import java.lang.reflect.Type

/**
 * <p>
 * @author zuhaib ahmed
 */
class MostViewArticleRequest() : NyTimeBaseRequest() {
    override fun endpoint(): String = "v2/mostviewed/all-sections/7.json"


    override fun requestMethod(): String = RequestMethod.GET

    override fun postBody(): JsonObject = JsonObject()

    override fun responseType(): Type = object : TypeToken<MostViewArticleModel?>() {}.type!!

    override fun offlineResponsePath(): String = "mostViewArticle.json"






}
