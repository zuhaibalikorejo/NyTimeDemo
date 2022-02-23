package com.nytime.nytimes

import com.nytime.nytimes.client.request.ApiResponse
import com.nytime.nytimes.client.request.ErrorResponse
import com.nytime.ui.MostViewRepository
import com.nytime.ui.model.MostViewArticleModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MostViewArticleRepositoryTest {

    lateinit var mostViewRepository: MostViewRepository


    @Before
    fun setup() {
        mostViewRepository = MostViewRepository(MockRequestExecutor(false))
    }


    @Test
    fun testFetchCustomerDetails() {

        mostViewRepository.mostViewArticle(
            object : ApiResponseListener<MostViewArticleModel> {

                override fun onApiSuccess(response: ApiResponse<MostViewArticleModel>) {
                    response?.data?.let {
                        Assert.assertNotNull(response)
                        Assert.assertNotNull(response?.data)
                    }
                }

                override fun onApiFailure(errorResponse: ErrorResponse) {
                }
            })
    }





}