package com.example.data.dataSource.remote

import com.example.data.footballModel.FootballModel
import retrofit2.Response

interface RemoteDataSource {

    suspend fun getAllMatches(): Response<FootballModel>

}