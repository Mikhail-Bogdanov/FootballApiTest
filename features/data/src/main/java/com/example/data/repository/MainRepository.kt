package com.example.data.repository

import com.example.data.footballModel.Data
import com.example.data.footballModel.FootballModel

interface MainRepository {

    suspend fun getRemoteDataBody(): List<Data>?

}