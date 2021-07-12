package com.danielvilha.fallenmeteors.ui.home.adapter

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.danielvilha.fallenmeteors.R
import com.danielvilha.fallenmeteors.network.FallenMeteorProperty
import com.danielvilha.fallenmeteors.ui.home.FallenMeteorsStatus

/**
 * Created by danielvilha on 08/07/21
 * https://github.com/danielvilha
 */

/**
 * When there is no Fallen Meteors property data (data is null), hide the [RecyclerView], otherwise show it.
 */
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<FallenMeteorProperty>?) {
    val adapter = recyclerView.adapter as FallenMeteorAdapter
    adapter.submitList(data)
}

/**
 * Working with my API status in LOADING, ERROR or DONE
 */
@BindingAdapter("fallenMeteorApiStatus")
fun bindStatus(statusImageView: ImageView, status: FallenMeteorsStatus) {
    when (status) {
        FallenMeteorsStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        FallenMeteorsStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        FallenMeteorsStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}