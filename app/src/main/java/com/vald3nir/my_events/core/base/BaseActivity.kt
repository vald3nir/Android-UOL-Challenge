package com.vald3nir.my_events.core.base

import android.content.DialogInterface
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.vald3nir.my_events.R

open class BaseActivity : AppCompatActivity() {

    fun showAlertDialog(message: Int, exitScreen: Boolean = false) {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.alert_))
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setMessage(getString(message))
            .setPositiveButton(android.R.string.ok) { dialogInterface: DialogInterface, _: Int ->
                dialogInterface.dismiss()
                if (exitScreen) finish()
            }.show()
    }

    fun showToast(message: Int) {
        Toast.makeText(this, getString(message), Toast.LENGTH_SHORT).show()
    }
}