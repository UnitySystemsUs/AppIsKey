package com.example.appiskey.photolisting.di

import com.example.appiskey.base.network.ApiClient
import com.example.appiskey.photolisting.data.PixabayPhotoApi
import com.example.appiskey.photolisting.data.PixabayPhotoApiService
import com.example.appiskey.photolisting.data.PixabayPhotoRepo
import com.example.appiskey.photolisting.domain.PixabayPhotoRepoImp
import com.example.appiskey.photolisting.domain.PixabayPhotoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PixabayPhotoModule {

    @Singleton
    @Provides
    fun provideHomeApi(apiClient: ApiClient): PixabayPhotoApi = PixabayPhotoApiService(apiClient)

    @Singleton
    @Provides
    fun provideHomeApiRepo(api: PixabayPhotoApi): PixabayPhotoRepo = PixabayPhotoRepoImp(api)

    @Singleton
    @Provides
    fun provideHomeUseCase(pixabayPhotoRepo: PixabayPhotoRepo): PixabayPhotoUseCase = PixabayPhotoUseCase(pixabayPhotoRepo)

}