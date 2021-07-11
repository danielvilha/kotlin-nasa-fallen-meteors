package com.danielvilha.fallenmeteors.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.danielvilha.fallenmeteors.databinding.ItemFallenMeteoriteBinding
import com.danielvilha.fallenmeteors.network.FallenMeteorProperty

/**
 * Created by danielvilha on 06/07/21
 * https://github.com/danielvilha
 *
 * This class implements a [RecyclerView] [ListAdapter] which uses Data Binding to present [List]
 * data, including computing diffs between lists.
 */
class FallenMeteorAdapter(private val onClickListener: OnClickListener): ListAdapter<FallenMeteorProperty, FallenMeteorAdapter.FallenMeteorViewHolder>(DiffCallback) {

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of [FallenMeteorProperty]
     * has been updated.
     */
    companion object DiffCallback: DiffUtil.ItemCallback<FallenMeteorProperty>() {
        override fun areItemsTheSame(oldItem: FallenMeteorProperty, newItem: FallenMeteorProperty): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: FallenMeteorProperty, newItem: FallenMeteorProperty): Boolean {
            return oldItem.id == newItem.id
        }
    }

    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FallenMeteorViewHolder {
        return FallenMeteorViewHolder(ItemFallenMeteoriteBinding.inflate(LayoutInflater.from(parent.context)))
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: FallenMeteorViewHolder, position: Int) {
        val property = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(property)
        }
        holder.bind(property)
    }

    /**
     * The FallenMeteorViewHolder constructor takes the binding variable from the associated
     * ItemFallenMeteoriteBinding and provides access to all [FallenMeteorProperty] information.
     */
    class FallenMeteorViewHolder(private var binding: ItemFallenMeteoriteBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(property: FallenMeteorProperty) {
            binding.property = property
            binding.executePendingBindings()
        }
    }

    class OnClickListener(val clickListener: (property: FallenMeteorProperty) -> Unit) {
        fun onClick(property: FallenMeteorProperty) = clickListener(property)
    }
}

