package com.vald3nir.my_events.ui.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.vald3nir.my_events.R
import com.vald3nir.my_events.databinding.ActivityListEventsBinding
import com.vald3nir.my_events.ui.details.EventDetailsActivity
import kotlinx.android.synthetic.main.activity_list_events.*

class ListEventsActivity : AppCompatActivity() {

    private var viewModel: ListEventsViewModel? = null

    private val adapter: ListEventsAdapter by lazy {
        ListEventsAdapter(object : ListEventsAdapter.IListEventsAdapter {
            override fun seeDetail(id: String?) {
                EventDetailsActivity.startEventDetailsActivity(
                    this@ListEventsActivity,
                    eventID = id
                )
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_events)

        setupViewModel()
        initObservers()

        initViews()
    }

    private fun setupViewModel() {
        val activityBinding: ActivityListEventsBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_list_events
        )
        viewModel = ViewModelProvider(this).get(ListEventsViewModel::class.java)
        activityBinding.model = viewModel
    }

    private fun initViews() {

        rvListEvents.setHasFixedSize(true)
        rvListEvents.adapter = adapter

        srlReloadList.setOnRefreshListener { viewModel?.loadEvents() }
        srlReloadList.isRefreshing = true

        viewModel?.loadEvents()
    }

    private fun initObservers() {

        viewModel?.error?.observe(this, {
            srlReloadList?.isRefreshing = false
            rvListEvents?.isVisible = false
            txvMessage?.isVisible = true
        })

        viewModel?.listOfEvents?.observe(this, {
            srlReloadList?.isRefreshing = false
            rvListEvents?.isVisible = true
            txvMessage?.isVisible = false
            adapter.events = it
        })
    }
}