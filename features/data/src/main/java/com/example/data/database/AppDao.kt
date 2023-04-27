package com.example.data.database

import androidx.room.*
import com.example.data.database.model.DataModel

@Dao
interface AppDao {

    @Query("SELECT * FROM mainTable")
    fun getAll(): List<DataModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(noteModel: DataModel)

    @Update
    fun update(noteModel: DataModel)

    @Query("DELETE FROM mainTable")
    fun clearBD()
}