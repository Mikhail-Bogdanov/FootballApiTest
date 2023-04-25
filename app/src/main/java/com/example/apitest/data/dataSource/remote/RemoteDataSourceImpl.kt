package com.example.apitest.data.dataSource.remote

import com.example.apitest.data.FootballApi
import com.example.apitest.footballModel.FootballModel
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response
import javax.inject.Inject


class RemoteDataSourceImpl @Inject constructor(
    private val footballApi: FootballApi
) : RemoteDataSource {

    override suspend fun getAllMatches(): Response<FootballModel>{
        return footballApi.getData()
    }

}