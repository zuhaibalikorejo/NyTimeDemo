package com.nytime.nytimes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule

import com.nytime.ui.MostViewActivityViewModel
import com.nytime.ui.MostViewRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MostViewArticleViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var mockRequestExecutor: MockRequestExecutor
    private lateinit var mostViewRepository: MostViewRepository

    private lateinit var viewmodel: MostViewActivityViewModel

    @Before
    fun setup() {
        mockRequestExecutor = MockRequestExecutor(false)
        mostViewRepository = MostViewRepository(mockRequestExecutor)
        viewmodel = MostViewActivityViewModel(
            mostViewRepository,
        )

        viewmodel.fetchMostViewArticle()
    }

    @Test
    fun testMostViewLiveData_null() {
        Assert.assertNotNull(viewmodel.mostViewLiveData.value)
    }


    @Test
    fun testResultLiveData_null() {
        Assert.assertNotNull(viewmodel.mostViewLiveData.value!!.results)
    }

    @Test
    fun testResultTitleData_null() {
        Assert.assertNotNull(viewmodel.mostViewLiveData.value!!.results?.get(0)?.title)
    }

    @Test
    fun testpublishdatenull() {
        Assert.assertNotNull(viewmodel.mostViewLiveData.value!!.results?.get(0)?.published_date)
    }


    @Test
    fun testurlnull() {
        Assert.assertNotNull(viewmodel.mostViewLiveData.value!!.results?.get(0)?.url)
    }

    @Test
    fun testResultUrlata_null() {
        Assert.assertNotNull(viewmodel.mostViewLiveData.value!!.results?.get(0)?.url)
    }






}


