package com.example.apitest.data.repository

import com.example.apitest.footballModel.FootballModel

interface MainRepository {

    suspend fun getRemoteDataBody(): FootballModel?

}