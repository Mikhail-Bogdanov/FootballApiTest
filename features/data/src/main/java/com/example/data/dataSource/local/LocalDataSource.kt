package com.example.data.dataSource.local

import com.example.data.database.model.DataModel

interface LocalDataSource {

    suspend fun getAllDataFromDatabase(): List<DataModel>

    suspend fun insertItem(dataModel: DataModel)

    suspend fun updateItem(dataModel: DataModel)

    suspend fun clearDatabase()
}