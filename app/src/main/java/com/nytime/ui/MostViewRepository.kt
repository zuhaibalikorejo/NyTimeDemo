package com.nytime.ui

import com.nytime.base.BaseRepository
import com.nytime.common.MostViewArticleRequest
import com.nytime.nytimes.ApiResponseListener
import com.nytime.nytimes.client.RequestExecutor

import com.nytime.ui.model.MostViewArticleModel
import javax.inject.Inject

/**
 * Activity ViewModel class for application's dashboard
 * <p>
 * @author zuhaib
 * Email: zuhaib.ahmed@adib.com
 * Company: zuhaib
 */
class MostViewRepository @Inject constructor(requestExecutor: RequestExecutor) : BaseRepository(requestExecutor) {

    fun mostViewArticle(listener: ApiResponseListener<MostViewArticleModel>) {
        requestExecutor.execute(MostViewArticleRequest(), listener)
    }


}