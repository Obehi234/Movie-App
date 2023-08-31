package com.example.streammoviesapplication.data.di

import com.example.streammoviesapplication.network.ApiKeyInterceptor
import com.example.streammoviesapplication.network.MovieService
import com.example.streammoviesapplication.utils.Constants.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(apiKey: String) : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(ApiKeyInterceptor(apiKey))
            .build()
    }

    @Singleton
    @Provides
    fun getRetrofitServiceInstance(retrofit: Retrofit) : MovieService {
        return retrofit.create(MovieService::class.java)
    }

    @Singleton
    @Provides
    fun getRetrofitInstance() : Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client: OkHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .build()

        val gson: Gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

}