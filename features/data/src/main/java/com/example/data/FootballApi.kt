package com.example.data

import com.example.data.footballModel.FootballModel
import retrofit2.Response
import retrofit2.http.GET

interface FootballApi {

    @GET("games?seasons[]=2022&postseason=true")
    suspend fun getData(): Response<FootballModel>

}