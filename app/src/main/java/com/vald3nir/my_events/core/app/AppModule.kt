package com.vald3nir.my_events.core.app

import com.vald3nir.my_events.ui.checkin.CheckInViewModel
import com.vald3nir.my_events.ui.details.DetailEventViewModel
import com.vald3nir.my_events.ui.list.ListEventsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {


    viewModel {
        CheckInViewModel(
            dataRepository = get()
        )
    }

    viewModel {
        DetailEventViewModel(
            dataRepository = get()
        )
    }

    viewModel {
        ListEventsViewModel(
            dataRepository = get()
        )
    }
}