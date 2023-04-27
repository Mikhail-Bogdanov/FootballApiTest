package com.example.data.repository

import com.example.data.dataSource.local.LocalDataSource
import com.example.data.dataSource.remote.RemoteDataSource
import com.example.data.database.model.DataModel
import com.example.data.footballModel.Data

import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : MainRepository {

    override suspend fun getRemoteDataList(): List<Data>? {
        return remoteDataSource.getAllMatches().body()?.data
    }

    override suspend fun getLocalDataList(): List<DataModel> =
        localDataSource.getAllDataFromDatabase()

    override suspend fun insertData(dataModel: DataModel) {
        localDataSource.insertItem(dataModel)
    }

    override suspend fun updateData(dataModel: DataModel) {
        localDataSource.updateItem(dataModel)
    }

    override suspend fun clearDatabase() {
        localDataSource.clearDatabase()
    }
}