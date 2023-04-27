package com.example.apitest.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.database.model.DataModel
import com.example.data.footballModel.Data
import com.example.data.repository.MainRepository
import com.example.data.utility.Event
import com.example.data.utility.Mapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private val privateStateFlow: MutableStateFlow<List<DataModel>?> = MutableStateFlow(null)
    var stateFlowStat = privateStateFlow
        private set

    private val eventChannel = Channel<Event>(Channel.BUFFERED)
    var eventsFlow = eventChannel.receiveAsFlow()
        private set

    /**
     * функция которая получает локальные данные из бд и дает их для отрисовки UI
     * потом получает данные из интернета и дает их для отрисовки UI снова
     * потом очищает базу данных
     * потом записывает все данные в базу
     */
    fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching{
                mainRepository.getLocalDataList()
            }.onSuccess { dataModel ->
                /**
                 * обновление UI по не самой актуальной информации (из БД)
                 */
                stateFlowStat.value = dataModel
                getRemoteData()
            }.onFailure {
                eventChannel.send(Event.NegativeFeedback("Failure getting data from database"))
            }
        }
    }

    private fun getRemoteData(){
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                mainRepository.getRemoteDataList() //getting data from API
            }.onSuccess { data ->
                /**
                 * когда мы получили более актуальную дату (из инета)
                 * мапим ее и обновляем UI снова
                 */
                var dataModelList = listOf<DataModel>()
                if(data != null){
                    for(i in data.indices){
                        dataModelList += Mapper.mapDataToDataModel(data[i])
                    }
                    clearData(data)
                }
                stateFlowStat.value = dataModelList
            }.onFailure {
                eventChannel.send(Event.NegativeFeedback("Failure getting remote data"))
            }
        }
    }

    private fun clearData(data: List<Data>){
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                mainRepository.clearDatabase()
            }.onSuccess {
                insertData(data)
            }.onFailure {
                eventChannel.send(Event.NegativeFeedback("Failure clearing database"))
            }
        }
    }

    private fun insertData(data: List<Data>){
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                /**
                 * маппинг и запись в базу данных
                 */
                for(i in data.indices){
                    val dataModel = Mapper.mapDataToDataModel(data[i])
                    mainRepository.insertData(dataModel)
                }
            }.onSuccess {
                eventChannel.send(Event.PositiveFeedback("All tasks completed"))
            }.onFailure {
                eventChannel.send(Event.NegativeFeedback("Failure inserting in database"))
            }
        }
    }
}