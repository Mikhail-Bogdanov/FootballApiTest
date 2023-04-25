package com.example.apitest.data.repository

import com.example.apitest.data.dataSource.local.LocalDataSource
import com.example.apitest.data.dataSource.remote.RemoteDataSource
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {



}