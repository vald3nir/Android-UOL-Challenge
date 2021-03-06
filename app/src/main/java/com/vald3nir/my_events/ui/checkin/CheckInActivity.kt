package com.vald3nir.my_events.ui.checkin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.vald3nir.my_events.R
import com.vald3nir.my_events.core.base.BaseActivity
import com.vald3nir.my_events.databinding.ActivityCheckinBinding
import kotlinx.android.synthetic.main.activity_checkin.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CheckInActivity : BaseActivity() {

    private val viewModel by viewModel<CheckInViewModel>()
    private lateinit var activityBinding: ActivityCheckinBinding

    companion object {

        private const val EVENT_ID = "EVENT_ID"

        fun startCheckInActivity(activity: Activity, eventID: String?) {
            val intent = Intent(activity, CheckInActivity::class.java)
            intent.putExtra(EVENT_ID, eventID)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkin)
        setupViewModel()
        initObservers()
        initViews()
    }

    private fun setupViewModel() {
        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_checkin)
        viewModel.init(intent)
    }

    private fun initObservers() {
        activityBinding.cancelListener = View.OnClickListener { finish() }
        activityBinding.checkInListener = View.OnClickListener { checkInAction() }

        viewModel.success.observe(this, { showToast(it) })
        viewModel.error.observe(this, { showAlertDialog(it) })

        viewModel.emailError.observe(this, {
            activityBinding.txtEmailError.visibility = View.VISIBLE
            activityBinding.txtEmailError.text = getString(it)
        })

        viewModel.nameError.observe(this, {
            activityBinding.txtNameError.visibility = View.VISIBLE
            activityBinding.txtNameError.text = getString(it)
        })
    }

    private fun checkInAction() {
        activityBinding.txtEmailError.visibility = View.GONE
        activityBinding.txtNameError.visibility = View.GONE

        viewModel.makeCheckIn(
            email = activityBinding.tietEmail.text.toString().trim(),
            name = activityBinding.tietName.text.toString().trim()
        )
    }

    private fun initViews() {
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener { finish() }
    }
}