package com.example.data.dataSource.local

import com.example.data.database.AppDatabase
import com.example.data.database.model.DataModel
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val appDatabase: AppDatabase
) : LocalDataSource {

    override suspend fun getAllDataFromDatabase(): List<DataModel> =
        appDatabase.noteDao().getAll()

    override suspend fun insertItem(dataModel: DataModel) {
        appDatabase.noteDao().insert(dataModel)
    }

    override suspend fun updateItem(dataModel: DataModel) {
        appDatabase.noteDao().update(dataModel)
    }

    override suspend fun clearDatabase() {
        appDatabase.noteDao().clearBD()
    }
}