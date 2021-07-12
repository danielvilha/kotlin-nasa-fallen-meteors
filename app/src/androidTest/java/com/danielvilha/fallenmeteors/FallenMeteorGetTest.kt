package com.danielvilha.fallenmeteors

import android.util.Log
import com.danielvilha.fallenmeteors.network.FallenMeteorApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by danielvilha on 06/07/21
 * https://github.com/danielvilha
 */
@RunWith(MockitoJUnitRunner::class)
class FallenMeteorGetTest {

    @Test
    fun getFallenMeteorsIsNotEmpty() {
        runBlocking {
            val fallenMeteors = FallenMeteorApi.retrofitService.getFallenMeteorsAsync(
                "year>='1900-1-01T00:00:00.000'",
                 1000,
                0
            )

            try {
                val fallen = fallenMeteors.await()
                Log.d(FallenMeteorGetTest::class.java.name, fallen.size.toString())
                assert(fallen.isNotEmpty())
            } catch (t: Throwable) {
                Log.e(FallenMeteorGetTest::class.java.name, t.localizedMessage)
            }
        }
    }
}