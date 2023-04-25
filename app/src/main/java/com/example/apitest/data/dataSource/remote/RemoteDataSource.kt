package com.example.apitest.data.dataSource.remote

import com.example.apitest.footballModel.FootballModel
import retrofit2.Response

interface RemoteDataSource {

    suspend fun getAllMatches(): Response<FootballModel>

}