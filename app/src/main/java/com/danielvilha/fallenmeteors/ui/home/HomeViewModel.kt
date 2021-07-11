package com.danielvilha.fallenmeteors.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danielvilha.fallenmeteors.network.FallenMeteorApi
import com.danielvilha.fallenmeteors.network.FallenMeteorProperty
import kotlinx.coroutines.launch

enum class FallenMeteorsStatus { LOADING, ERROR, DONE }
/**
 * Created by danielvilha on 06/07/21
 * https://github.com/danielvilha
 *
 * The [ViewModel] that is attached to the [HomeFragment].
 */
class HomeViewModel : ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<FallenMeteorsStatus>()
    // The external immutable LiveData for the request status
    val status: LiveData<FallenMeteorsStatus>
        get() = _status

    // Internally, I use a MutableLiveData, because I will be updating the List of FallenMeteorProperty
    // with new values
    private var _meteors = MutableLiveData<List<FallenMeteorProperty>>()
    // The external LiveData interface to the property is immutable, so only this class can modify
    val meteors: LiveData<List<FallenMeteorProperty>>
        get() = _meteors

    private val _navigateToSelectedProperty = MutableLiveData<FallenMeteorProperty?>()
    val navigateToSelectedProperty: LiveData<FallenMeteorProperty?>
        get() = _navigateToSelectedProperty

    /**
     * Call getFallenMeteorProperties() on init so we can display status immediately.
     */
    init {
        getFallenMeteorProperties()
    }

    /**
     * Get Fallen Meteors list property information from the Fallen Meteor Retrofit service and updates the
     * [FallenMeteorProperty] [List] [LiveData]. The Retrofit service returns a coroutine Deferred, which we
     * await to get the result of the transaction.
     */
    private fun getFallenMeteorProperties() {
        viewModelScope.launch {
            val fallenMeteors = FallenMeteorApi.retrofitService.getFallenMeteorsAsync("year>='1900-1-01T00:00:00.000'")

            try {
                _status.value = FallenMeteorsStatus.LOADING
                val listResult = fallenMeteors.await()
                _status.value = FallenMeteorsStatus.DONE
                _meteors.value = listResult
            } catch (t: Throwable) {
                _status.value = FallenMeteorsStatus.ERROR
                _meteors.value = ArrayList()
            }
        }
    }

    fun displayPropertyDetails(property: FallenMeteorProperty) {
        _navigateToSelectedProperty.value = property
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }
}