package com.vald3nir.my_events.ui.details

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vald3nir.data.mapper.toEventItemView
import com.vald3nir.data.models.EventItemView
import com.vald3nir.my_events.core.BaseViewModel
import com.vald3nir.my_events.ui.details.EventDetailsActivity.Companion.EVENT_ID
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DetailEventViewModel : BaseViewModel() {

    private var eventId: String? = null

    private val _error = MutableLiveData<Unit>()
    val error: LiveData<Unit> = _error

    private val _itemView = MutableLiveData<EventItemView>()
    val itemView: LiveData<EventItemView> = _itemView

    fun init(intent: Intent?) {
        this.eventId = intent?.getStringExtra(EVENT_ID)
    }

    fun loadEvent() {
        launch {
            var eventItemView: EventItemView?
            withContext(Dispatchers.IO) {
                eventItemView = dataController.getEvent(eventId)?.toEventItemView()
            }
            if (eventItemView != null) {
                _itemView.postValue(eventItemView!!)
            } else {
                _error.postValue(Unit)
            }
        }
    }
}