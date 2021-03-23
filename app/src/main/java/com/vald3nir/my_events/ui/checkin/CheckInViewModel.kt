package com.vald3nir.my_events.ui.checkin

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vald3nir.data.exceptions.InvalidEmailException
import com.vald3nir.data.exceptions.InvalidNameException
import com.vald3nir.my_events.R
import com.vald3nir.my_events.core.BaseViewModel
import com.vald3nir.my_events.ui.details.EventDetailsActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CheckInViewModel : BaseViewModel() {

    private var eventId: String? = null

    private val _error = MutableLiveData<Int>()
    val error: LiveData<Int> = _error

    private val _emailError = MutableLiveData<Int>()
    val emailError: LiveData<Int> = _emailError

    private val _nameError = MutableLiveData<Int>()
    val nameError: LiveData<Int> = _nameError

    private val _success = MutableLiveData<Int>()
    val success: LiveData<Int> = _success


    fun init(intent: Intent?) {
        this.eventId = intent?.getStringExtra(EventDetailsActivity.EVENT_ID)
    }

    fun makeCheckIn(email: String?, name: String?) {
        launch {
            var exception: Exception? = null

            withContext(Dispatchers.IO) {
                try {
                    dataController.makeCheckIn(eventId = eventId, email = email, name = name)
                } catch (e: Exception) {
                    exception = e
                }
            }
            if (exception != null) {
                when (exception) {
                    is InvalidNameException -> _nameError.postValue(R.string.invalid_name_message)
                    is InvalidEmailException -> _emailError.postValue(R.string.invalid_email_message)
                    else -> _error.postValue(R.string.error_generic_message)
                }
            } else {
                _success.postValue(R.string.success_check_in_message)
            }
        }
    }
}