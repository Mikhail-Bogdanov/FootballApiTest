package com.example.apitest.domain.viewModels

import androidx.lifecycle.ViewModel
import com.example.apitest.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SplashViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {



}