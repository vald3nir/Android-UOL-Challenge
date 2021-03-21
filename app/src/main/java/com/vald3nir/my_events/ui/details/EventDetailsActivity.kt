package com.vald3nir.my_events.ui.details

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.vald3nir.my_events.R
import com.vald3nir.my_events.databinding.ActivityEventDetailsBinding
import kotlinx.android.synthetic.main.activity_event_details.*


class EventDetailsActivity : AppCompatActivity() {

    private var viewModel: DetailEventViewModel? = null
    private lateinit var activityBinding: ActivityEventDetailsBinding

    companion object {

        const val EVENT_ID = "EVENT_ID"

        fun startEventDetailsActivity(activity: Activity, eventID: String?) {
            val intent = Intent(activity, EventDetailsActivity::class.java)
            intent.putExtra(EVENT_ID, eventID)
            activity.startActivity(intent)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_details)
        setupViewModel()
        initObservers()
        initViews()
        viewModel?.loadEvent()
    }

    private fun setupViewModel() {
        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_event_details)
        viewModel = ViewModelProvider(this).get(DetailEventViewModel::class.java)
        viewModel?.init(intent)

    }

    private fun initObservers() {
        viewModel?.error?.observe(this, {
            showAlertDialog()
        })

        viewModel?.itemView?.observe(this, {
            activityBinding.itemView = it
        })
    }

    private fun initViews() {
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun showAlertDialog() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.alert_))
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setMessage(getString(R.string.could_not_load_event))
            .setPositiveButton(android.R.string.ok) { dialogInterface: DialogInterface, i: Int ->
                dialogInterface.dismiss()
                onBackPressed()
            }
            .show()
    }
}