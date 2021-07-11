package com.danielvilha.fallenmeteors.ui.meteor

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.danielvilha.fallenmeteors.R
import com.danielvilha.fallenmeteors.network.FallenMeteorProperty
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

/**
 * Created by danielvilha on 08/07/21
 * https://github.com/danielvilha
 *
 * The ViewModel associated with the [MeteorFragment], containing information about the selected
 * [FallenMeteorProperty].
 */
class MeteorViewModel(property: FallenMeteorProperty, app: Application): AndroidViewModel(app) {

    private val _selectedProperty = MutableLiveData<FallenMeteorProperty>()
    // The external LiveData for the SelectedProperty
    val selectedProperty: LiveData<FallenMeteorProperty>
        get() = _selectedProperty

    val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulate the map as soon as available.
         * This callback is triggered when the map is ready to be used.
         * This is where I add bookmarks, add listeners, and move the camera.
         */
        val latLng = LatLng(
            _selectedProperty.value!!.geolocation!!.latitude.toDouble(),
            _selectedProperty.value!!.geolocation!!.longitude.toDouble())
        googleMap.addMarker(MarkerOptions().position(latLng)
            .title("${_selectedProperty.value!!.geolocation!!.latitude}°, ${_selectedProperty.value!!.geolocation!!.longitude}°")
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_vector_icon))
        )
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
    }

    // Initialize the _selectedProperty MutableLiveData
    init {
        _selectedProperty.value = property
    }
}