package com.nytime.base

import androidx.lifecycle.ViewModel
import com.nytime.nytimes.client.request.ErrorResponse
import com.nytime.nytimes.retrofit.RetrofitRequestExecutorImpl
import com.nytime.nytimes.utils.SingleLiveEvent


/**
 * <p>
 * @author zuhaib ahmed
 */

abstract class BaseViewModel :
    ViewModel() {
    val networkRequestProgress = SingleLiveEvent<Boolean>()
    val networkFailed = SingleLiveEvent<ErrorResponse?>()
    var baseRepository: BaseRepository? = null

    init {
        baseRepository = BaseRepository(RetrofitRequestExecutorImpl())
    }

}