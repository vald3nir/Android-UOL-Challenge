package com.vald3nir.my_events.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vald3nir.data.DataRepository
import com.vald3nir.data.mapper.toItemsListEvents
import com.vald3nir.data.models.EventItemView
import com.vald3nir.my_events.core.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ListEventsViewModel(private val dataRepository: DataRepository) : BaseViewModel() {

    private val _error = MutableLiveData<Unit>()
    val error: LiveData<Unit> = _error

    private val _listOfEvents = MutableLiveData<List<EventItemView>>()
    val listOfEvents: LiveData<List<EventItemView>> = _listOfEvents


    fun loadEvents() {

        launch {
            var list: List<EventItemView>?
            withContext(Dispatchers.IO) {
                list = dataRepository.listEvents()?.toItemsListEvents()
            }
            if (list.isNullOrEmpty()) {
                _error.postValue(Unit)
            } else {
                _listOfEvents.postValue(list!!)
            }
        }
    }


}