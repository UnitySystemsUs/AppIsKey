package com.example.appiskey.di

import com.example.appiskey.network.ApiClient
import com.example.appiskey.data.HitApi
import com.example.appiskey.data.HitApiService
import com.example.appiskey.data.HomeRepo
import com.example.appiskey.domain.HomeRepoImp
import com.example.appiskey.domain.HomeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HomeModule {

    @Singleton
    @Provides
    fun provideHomeApi(apiClient: ApiClient): HitApi = HitApiService(apiClient)

    @Singleton
    @Provides
    fun provideHomeApiRepo(api: HitApi): HomeRepo = HomeRepoImp(api)

    @Singleton
    @Provides
    fun provideHomeUseCase(homeRepo: HomeRepo): HomeUseCase = HomeUseCase(homeRepo)

}