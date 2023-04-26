package com.example.data.dataSource.local

import com.example.data.FootballApi
import javax.inject.Inject


class LocalDataSourceImpl @Inject constructor(
    private val footballApi: FootballApi
) : LocalDataSource {



}