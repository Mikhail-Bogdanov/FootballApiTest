package com.example.apitest.di

import android.content.Context
import androidx.room.Room
import com.example.data.FootballApi
import com.example.data.dataSource.local.LocalDataSource
import com.example.data.dataSource.local.LocalDataSourceImpl
import com.example.data.dataSource.remote.RemoteDataSource
import com.example.data.dataSource.remote.RemoteDataSourceImpl
import com.example.data.database.AppDatabase
import com.example.data.repository.MainRepository
import com.example.data.repository.MainRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance(): FootballApi {

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://www.balldontlie.io/api/v1/")
            .client(httpClient.build())
            .build()
            .create(FootballApi::class.java) as FootballApi
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(appDatabase: AppDatabase): LocalDataSource =
        LocalDataSourceImpl(appDatabase)

    @Provides
    @Singleton
    fun providesRemoteDataSource(footballApi: FootballApi): RemoteDataSource =
        RemoteDataSourceImpl(footballApi)

    @Provides
    @Singleton
    fun provideMainRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ): MainRepository =
        MainRepositoryImpl(
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource
        )

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room
            .databaseBuilder(appContext, AppDatabase::class.java, "football-database")
            .build()
    }
}