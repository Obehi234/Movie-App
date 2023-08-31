package com.example.streammoviesapplication.data.di

import com.example.streammoviesapplication.network.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun getRetrofitServiceInstance(retrofit: Retrofit) : MovieService {
        return retrofit.create(MovieService::class.java)
    }

}