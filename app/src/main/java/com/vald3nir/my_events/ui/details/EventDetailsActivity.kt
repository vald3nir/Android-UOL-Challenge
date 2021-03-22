package com.vald3nir.my_events.ui.details

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.vald3nir.my_events.R
import com.vald3nir.my_events.core.BaseActivity
import com.vald3nir.my_events.databinding.ActivityEventDetailsBinding
import com.vald3nir.my_events.ui.chekin.CheckInActivity
import com.vald3nir.my_events.ui.map.MapsActivity
import kotlinx.android.synthetic.main.activity_event_details.*


class EventDetailsActivity : BaseActivity() {

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

        activityBinding.checkInListener = View.OnClickListener { goToCheckInActivity() }
        activityBinding.shareListener = View.OnClickListener { viewModel?.shareContent() }

        viewModel?.errorLoadEvent?.observe(this, { showErrorLoadEventAlertDialog() })
        viewModel?.errorShare?.observe(this, { showErrorShareAlertDialog() })

        viewModel?.contentToBeShare?.observe(this, { shareEvent(it) })

        viewModel?.itemView?.observe(this, { itemView ->
            activityBinding.itemView = itemView
            activityBinding.showMapListener = View.OnClickListener {
                goToSeeEventOnMap(
                    itemView.latitude?.toDouble(),
                    itemView.longitude?.toDouble()
                )
            }
        })
    }

    private fun initViews() {
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener { finish() }
    }

    private fun showErrorLoadEventAlertDialog() {
        showAlertDialog(R.string.could_not_load_event, exitScreen = true)
    }

    private fun showErrorShareAlertDialog() {
        showAlertDialog(R.string.it_was_not_possible_to_share_the_content)
    }

    private fun goToCheckInActivity() {
        CheckInActivity.startCheckInActivity(this, eventID = intent?.getStringExtra(EVENT_ID))
    }

    private fun goToSeeEventOnMap(latitude: Double?, longitude: Double?) {
        MapsActivity.startMapsActivity(this, latitude, longitude)
    }

    private fun shareEvent(text: String) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }
        startActivity(Intent.createChooser(sendIntent, getString(R.string.event)))
    }
}