package com.example.apitest.di

import com.example.apitest.data.FootballApi
import com.example.apitest.data.dataSource.local.LocalDataSource
import com.example.apitest.data.dataSource.local.LocalDataSourceImpl
import com.example.apitest.data.dataSource.remote.RemoteDataSource
import com.example.apitest.data.dataSource.remote.RemoteDataSourceImpl
import com.example.apitest.data.repository.MainRepositoryImpl
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

    @Provides
    @Singleton
    fun provideRetrofitInstance(): FootballApi{

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
    fun provideLocalDataSource(footballApi: FootballApi) = LocalDataSourceImpl(footballApi)

    @Provides
    @Singleton
    fun providesRemoteDataSource(footballApi: FootballApi) = RemoteDataSourceImpl(footballApi)

    @Provides
    @Singleton
    fun provideMainRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ) = MainRepositoryImpl(
        localDataSource = localDataSource,
        remoteDataSource =  remoteDataSource
    )

}