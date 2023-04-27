package com.example.data.dataSource.remote

import com.example.data.FootballApi
import com.example.data.footballModel.FootballModel
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val footballApi: FootballApi
) : RemoteDataSource {

    override suspend fun getAllMatches(): Response<FootballModel>{
        return footballApi.getData()
    }
}