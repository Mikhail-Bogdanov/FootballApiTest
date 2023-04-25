package com.example.apitest.data

import com.example.apitest.footballModel.FootballModel
import retrofit2.Response
import retrofit2.http.GET

interface FootballApi {

    @GET("games?seasons[]=2022&postseason=true")
    suspend fun getData(): Response<FootballModel>

}