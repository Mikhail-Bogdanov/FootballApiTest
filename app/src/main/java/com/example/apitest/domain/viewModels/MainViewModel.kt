package com.example.apitest.domain.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apitest.data.repository.MainRepository
import com.example.apitest.footballModel.FootballModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private val privateStateFlow: MutableStateFlow<FootballModel?> = MutableStateFlow(null)
    val stateFlowStat = privateStateFlow

    fun getStatistic(){
        viewModelScope.launch {
            runCatching {
                mainRepository.getRemoteDataBody()
            }.onSuccess { footballModel ->
                stateFlowStat.value = footballModel
            }.onFailure {
                stateFlowStat.value = null
            }
        }
    }

}