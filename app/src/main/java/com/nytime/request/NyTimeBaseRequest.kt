package com.nytime.request

import com.nytime.nytimes.BaseRequest

/**
 * <p>
 * @author zuhaib ahmed
 */
abstract class NyTimeBaseRequest : BaseRequest() {

    override fun toString(): String {
        return postBody().toString()
    }
}