package com.example.apitest.data.dataSource.local

import com.example.apitest.data.FootballApi
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


class LocalDataSourceImpl @Inject constructor(
    private val footballApi: FootballApi
) : LocalDataSource {



}