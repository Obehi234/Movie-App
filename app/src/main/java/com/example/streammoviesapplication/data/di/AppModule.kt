package com.example.streammoviesapplication.data.di

import android.content.Context
import androidx.room.Room
import com.example.streammoviesapplication.data.db.DocumentaryDao
import com.example.streammoviesapplication.data.db.HorrorMovieDao
import com.example.streammoviesapplication.data.db.MovieDetailsDao
import com.example.streammoviesapplication.data.db.MovieListDao
import com.example.streammoviesapplication.data.db.MoviesDatabase
import com.example.streammoviesapplication.data.db.RelatedMoviesDao
import com.example.streammoviesapplication.data.db.TVSeriesDao
import com.example.streammoviesapplication.data.db.TrendingMoviesDao
import com.example.streammoviesapplication.data.repository.DocumentaryRepositoryImpl
import com.example.streammoviesapplication.data.repository.HorrorMovieRepositoryImpl
import com.example.streammoviesapplication.data.repository.IDocumentaryRepository
import com.example.streammoviesapplication.data.repository.IHorrorMovieRepository
import com.example.streammoviesapplication.data.repository.IMoviesDetailsRepository
import com.example.streammoviesapplication.data.repository.IMoviesTabRepository
import com.example.streammoviesapplication.data.repository.IRelatedMoviesRepository
import com.example.streammoviesapplication.data.repository.ITVSeriesRepository
import com.example.streammoviesapplication.data.repository.ITrendingMoviesRepository
import com.example.streammoviesapplication.data.repository.MovieDetailsRepositoryImpl
import com.example.streammoviesapplication.data.repository.MoviesTabRepositoryImpl
import com.example.streammoviesapplication.data.repository.RelatedMoviesRepositoryImpl
import com.example.streammoviesapplication.data.repository.TVSeriesRepositoryImpl
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
    fun providesMovieListDao(database: MoviesDatabase): MovieListDao {
        return database.movieListDao()
    }

    @Singleton
    @Provides
    fun provideMovieDetailsDao(database: MoviesDatabase): MovieDetailsDao {
        return database.movieDetailsDao()
    }

    @Singleton
    @Provides
    fun provideRelatedMoviesDao(database: MoviesDatabase): RelatedMoviesDao {
        return database.relatedMoviesDao()
    }

    @Singleton
    @Provides
    fun providesTVSeriesDao(database: MoviesDatabase) : TVSeriesDao {
        return database.tvSeriesDao()
    }

    @Singleton
    @Provides
    fun providesDocumentaryDao(database: MoviesDatabase) : DocumentaryDao {
        return database.documentaryDao()
    }

    @Singleton
    @Provides
    fun providesHorrorMoviesDao(database: MoviesDatabase) : HorrorMovieDao {
        return database.horrorMoviesDao()
    }


    @Singleton
    @Provides
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Singleton
    @Provides
    fun provideTrendingMoviesRepository(
        api: MovieService,
        trendingMoviesDao: TrendingMoviesDao
    ): ITrendingMoviesRepository {
        return TrendingMoviesRepositoryImpl(api, trendingMoviesDao)
    }

    @Singleton
    @Provides
    fun provideMovieDetailsRepository(
        api: MovieService,
        movieDetailsDao: MovieDetailsDao
    ): IMoviesDetailsRepository {
        return MovieDetailsRepositoryImpl(api, movieDetailsDao)
    }

    @Singleton
    @Provides
    fun providesMovieListRepository(
        api: MovieService,
        movieListDao: MovieListDao
    ): IMoviesTabRepository {
        return MoviesTabRepositoryImpl(api, movieListDao)
    }

    @Singleton
    @Provides
    fun providesRelatedMoviesRepository(
        api: MovieService,
        relatedMoviesDao: RelatedMoviesDao
    ): IRelatedMoviesRepository {
        return RelatedMoviesRepositoryImpl(api, relatedMoviesDao)
    }

    @Singleton
    @Provides
    fun providesTVSeriesRepository(
        api: MovieService,
        tvSeriesDao: TVSeriesDao
    ): ITVSeriesRepository {
        return TVSeriesRepositoryImpl(api, tvSeriesDao)
    }

    @Singleton
    @Provides
    fun providesDocumentaryRepository(
        api: MovieService,
        documentaryDao: DocumentaryDao
    ): IDocumentaryRepository {
        return DocumentaryRepositoryImpl(api, documentaryDao)
    }

    @Singleton
    @Provides
    fun providesHorrorMoviesRepository(
        api: MovieService,
        horrorMovieDao: HorrorMovieDao
    ): IHorrorMovieRepository {
        return HorrorMovieRepositoryImpl(api, horrorMovieDao)
    }

}