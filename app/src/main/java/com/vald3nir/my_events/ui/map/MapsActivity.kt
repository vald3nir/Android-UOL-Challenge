package com.vald3nir.my_events.ui.map

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.vald3nir.my_events.R
import kotlinx.android.synthetic.main.activity_maps.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    companion object {

        const val LATITUDE = "LATITUDE"
        const val LONGITUDE = "LONGITUDE"

        fun startMapsActivity(activity: Activity, latitude: Double?, longitude: Double?) {
            val intent = Intent(activity, MapsActivity::class.java)
            intent.putExtra(LATITUDE, latitude)
            intent.putExtra(LONGITUDE, longitude)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener { finish() }

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val latitude = intent?.getDoubleExtra(LATITUDE, 0.0)
        val longitude = intent?.getDoubleExtra(LONGITUDE, 0.0)
        val location = LatLng(latitude!!, longitude!!)
        mMap = googleMap
        mMap.addMarker(MarkerOptions().position(location).title(getString(R.string.my_event)))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location))
    }
}