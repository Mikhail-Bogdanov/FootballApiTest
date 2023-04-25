package com.example.apitest.data.repository

import com.example.apitest.data.dataSource.local.LocalDataSource
import com.example.apitest.data.dataSource.remote.RemoteDataSource
import com.example.apitest.footballModel.FootballModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : MainRepository{

    override suspend fun getRemoteDataBody(): FootballModel? {
        return remoteDataSource.getAllMatches().body()
    }


}