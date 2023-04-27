package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.database.model.DataModel

@Database(entities = [DataModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): AppDao
}