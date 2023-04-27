package com.example.data.repository

import com.example.data.database.model.DataModel
import com.example.data.footballModel.Data

interface MainRepository {

    /**
     * data from API
     */
    suspend fun getRemoteDataList(): List<Data>?

    /**
     * data from database
     */
    suspend fun getLocalDataList(): List<DataModel>

    suspend fun insertData(dataModel: DataModel)

    suspend fun updateData(dataModel: DataModel)

    suspend fun clearDatabase()
}