package com.danielvilha.fallenmeteors.network

import kotlinx.coroutines.Deferred
import retrofit2.http.*

/**
 * Created by danielvilha on 06/07/21
 * https://github.com/danielvilha
 */
interface FallenMeteorApiService {

    /**
     * Returns a Coroutine [List] of [FallenMeteorProperty] which can be fetched with await() if in a Coroutine scope.
     * The @GET annotation indicates that the "real estate" endpoint will be requested with the GET
     * HTTP method
     */
    @Headers("app_token: nw9EDzbru6hpFCyYh2cpZmX6m")
    @GET("gh4g-9sfh.json?\$order=mass DESC NULLS LAST")
    fun getFallenMeteorsAsync(
        @Query("\$where", encoded = true) where: String,
        @Query("\$limit") limit: Int,
        @Query("\$offset") offset: Int
    ): Deferred<MutableList<FallenMeteorProperty>>
}