package com.danielvilha.fallenmeteors.network

import java.io.Serializable

/**
 * Created by danielvilha on 06/07/21
 * https://github.com/danielvilha
 *
 * Gets the meteorite list from the Fallen Meteor API Retrofit service and updates the
 * [FallenMeteorProperty]. The Retrofit service returns a coroutine deferred, which we
 * are waiting to obtain the result of the transaction.
 */
data class FallenMeteorProperty(
    val id: String,
    val name: String? = "This item has no name",
    val nametype: String? = "This item has no name type",
    val recclass: String? = "This item has no recclass",
    val mass: String? = "This item has no mass",
    val fall: String? = "This item has no fall",
    val year: String? = "0000",
    val reclat: String? = "This item has no reclat",
    val reclong: String? = "This item has no reclong",
    val geolocation: GeolocationProperty?
): Serializable

data class GeolocationProperty(
    val latitude: String,
    val longitude: String
): Serializable