package com.example.data.repository

import com.example.data.dataSource.local.LocalDataSource
import com.example.data.dataSource.remote.RemoteDataSource
import com.example.data.footballModel.Data
import com.example.data.footballModel.FootballModel

import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : MainRepository {

    override suspend fun getRemoteDataBody(): List<Data>? {
        return remoteDataSource.getAllMatches().body()?.data
    }


}