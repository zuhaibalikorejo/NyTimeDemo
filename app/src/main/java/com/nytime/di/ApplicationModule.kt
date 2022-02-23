package com.nytime.di


import com.nytime.nytimes.BuildConfig
import com.nytime.nytimes.client.RequestExecutor
import com.nytime.nytimes.retrofit.OfflineRequestExecutorImpl
import com.nytime.nytimes.retrofit.RetrofitRequestExecutorImpl

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
/**
 * <p>
 * @author zuhaib ahmed
 */
@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun get(): RequestExecutor =
    if (BuildConfig.OFFLINE) OfflineRequestExecutorImpl() else RetrofitRequestExecutorImpl()



}