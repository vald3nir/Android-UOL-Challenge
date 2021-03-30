package com.vald3nir.my_events.core

import androidx.lifecycle.ViewModel
import com.vald3nir.data.DataRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    private val viewModelJob = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    val dataController = DataRepository(MainApplication.applicationContext())


    fun launch(block: suspend () -> Unit) {
        uiScope.launch {
            block()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}