package com.danielvilha.fallenmeteors.ui.meteor

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.danielvilha.fallenmeteors.network.FallenMeteorProperty

/**
 * Created by danielvilha on 08/07/21
 * https://github.com/danielvilha
 */
class MeteorViewModelFactory(
    private val property: FallenMeteorProperty,
    private val application: Application
): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MeteorViewModel::class.java)) {
            return MeteorViewModel(property, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}