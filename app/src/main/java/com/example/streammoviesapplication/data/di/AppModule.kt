package com.example.streammoviesapplication.data.di

import android.content.Context
import androidx.room.Room
import com.example.streammoviesapplication.data.db.MoviesDatabase
import com.example.streammoviesapplication.data.db.TrendingMoviesDao
import com.example.streammoviesapplication.data.repository.TrendingMoviesRepository
import com.example.streammoviesapplication.data.repository.TrendingMoviesRepositoryImpl
import com.example.streammoviesapplication.network.ApiKeyInterceptor
import com.example.streammoviesapplication.network.MovieService
import com.example.streammoviesapplication.utils.Constants.API_KEY
import com.example.streammoviesapplication.utils.Constants.BASE_URL
import com.example.streammoviesapplication.utils.Constants.MOVIE_DATABASE
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
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
    fun getRetrofitServiceInstance(retrofit: Retrofit): MovieService {
        return retrofit.create(MovieService::class.java)
    }

    @Singleton
    @Provides
    fun getRetrofitInstance(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client: OkHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(ApiKeyInterceptor(apiKey = API_KEY))
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

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MoviesDatabase {
        return Room.databaseBuilder(
            context,
            MoviesDatabase::class.java,
            MOVIE_DATABASE
        )

            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providesTrendingMoviesDao(database: MoviesDatabase): TrendingMoviesDao {
        return database.trendingMoviesDao()
    }

    @Singleton
    @Provides
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Singleton
    @Provides
    fun provideTrendingMoviesRepository(
        api: MovieService,
        trendingMoviesDao: TrendingMoviesDao
    ): TrendingMoviesRepository {
        return TrendingMoviesRepositoryImpl(api, trendingMoviesDao)
    }

}