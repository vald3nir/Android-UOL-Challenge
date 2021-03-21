package com.vald3nir.my_events.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vald3nir.data.mapper.toItemsListEvents
import com.vald3nir.data.models.ItemListEvents
import com.vald3nir.my_events.core.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ListEventsViewModel : BaseViewModel() {

    private val _error = MutableLiveData<Unit>()
    val error: LiveData<Unit> = _error

    private val _listOfEvents = MutableLiveData<List<ItemListEvents>>()
    val listEvents: LiveData<List<ItemListEvents>> = _listOfEvents


    fun loadEvents() {

        launch {
            var ev: List<ItemListEvents>?
            withContext(Dispatchers.IO) {
                ev = dataController.listEvents()?.toItemsListEvents()
            }
            if (ev.isNullOrEmpty()) {
                _error.postValue(Unit)
            } else {
                _listOfEvents.postValue(ev!!)
            }
        }
    }


}