package com.example.apitest.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.footballModel.Data
import com.example.data.repository.MainRepository
import com.example.data.footballModel.FootballModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private val privateStateFlow: MutableStateFlow<List<Data>?> = MutableStateFlow(null)
    val stateFlowStat = privateStateFlow

    fun getStatistic(){
        viewModelScope.launch {
            runCatching {
                mainRepository.getRemoteDataBody()
            }.onSuccess { data ->
                stateFlowStat.value = data
            }.onFailure {
                stateFlowStat.value = null
            }
        }
    }

}