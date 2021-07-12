package com.danielvilha.fallenmeteors.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isEmpty
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.danielvilha.fallenmeteors.R
import com.danielvilha.fallenmeteors.databinding.FragmentHomeBinding
import com.danielvilha.fallenmeteors.ui.home.adapter.FallenMeteorAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.app_bar_main.*

/**
 * Created by danielvilha on 06/07/21
 * https://github.com/danielvilha
 *
 * This [Fragment] will show the list of the fallen meteors on Earth.
 */
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    /**
     * Lazily initialize our [HomeViewModel]
     */
    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Get a reference to the binding object and inflate the fragment views.
        binding = FragmentHomeBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

        // Sets the adapter of the fallenMeteors RecyclerView
        binding.recycler.adapter = FallenMeteorAdapter(FallenMeteorAdapter.OnClickListener {
            viewModel.displayPropertyDetails(it)
        })

        // Setting the swipe refresh in my recycler view
        binding.swipeRefresh.setOnRefreshListener {
            if (binding.recycler.isEmpty()) {
                viewModel.getFallenMeteorProperties()
                binding.recycler.adapter = FallenMeteorAdapter(FallenMeteorAdapter.OnClickListener {
                    viewModel.displayPropertyDetails(it)
                })
            } else {
                viewModel.getFallenMeteorProperties()
                binding.recycler.adapter!!.notifyDataSetChanged()
            }

            binding.swipeRefresh.isRefreshing = false
        }

        // Setting my server call when I reach the end of my list.
        binding.recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.getFallenMeteorProperties()
                    binding.recycler.adapter!!.notifyDataSetChanged()
                }
            }
        })

        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, {
            if (it != null) {
                if (it.geolocation == null) {
                    Snackbar.make(binding.recycler, getString(R.string.has_no_lat_lng), Snackbar.LENGTH_LONG).show()
                } else {
                    this.findNavController().navigate(HomeFragmentDirections.actionOpenMeteorFragment(it))
                    viewModel.displayPropertyDetailsComplete()
                }
            }
        })

        // Menu icon
        requireActivity().toolbar.setNavigationIcon(R.drawable.ic_menu_icon)
        requireActivity().toolbar.menu.setGroupVisible(0, true)

        return binding.root
    }
}