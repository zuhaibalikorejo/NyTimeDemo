package com.nytime.ui




import androidx.lifecycle.MutableLiveData
import com.nytime.base.BaseViewModel
import com.nytime.common.EspressoIdlingResourceTest
import com.nytime.nytimes.ApiResponseListener
import com.nytime.nytimes.client.request.ApiResponse
import com.nytime.nytimes.client.request.ErrorResponse
import com.nytime.ui.model.MostViewArticleModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Activity ViewModel class for application's dashboard
 * <p>
 * @author zuhaib
 * Email: zuhaib.ahmed@adib.com
 * Company: zuhaib
 */
@HiltViewModel
class MostViewActivityViewModel @Inject constructor(
        private val repository: MostViewRepository,
) : BaseViewModel() {


    var mostViewLiveData = MutableLiveData<MostViewArticleModel>()


    fun fetchMostViewArticle() {
        EspressoIdlingResourceTest.increment()
        repository.mostViewArticle(
            object : ApiResponseListener<MostViewArticleModel> {

                override fun onApiSuccess(response: ApiResponse<MostViewArticleModel>) {
                    EspressoIdlingResourceTest.decrement()
                    response?.data?.let {
                        mostViewLiveData.value = it
                    }
                }

                override fun onApiFailure(errorResponse: ErrorResponse) {
                    EspressoIdlingResourceTest.decrement()
                    networkFailed.value = errorResponse
                }
            })
    }









}