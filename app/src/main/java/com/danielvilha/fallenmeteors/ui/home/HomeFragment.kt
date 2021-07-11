package com.danielvilha.fallenmeteors.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isEmpty
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.danielvilha.fallenmeteors.R
import com.danielvilha.fallenmeteors.databinding.FragmentHomeBinding
import com.danielvilha.fallenmeteors.ui.home.adapter.FallenMeteorAdapter
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

        // Setting the refresh in my recycler view
        binding.swipeRefresh.setOnRefreshListener {
            if (binding.recycler.isEmpty()) {
                viewModel.getFallenMeteorProperties()
                binding.recycler.adapter = FallenMeteorAdapter(FallenMeteorAdapter.OnClickListener {
                    viewModel.displayPropertyDetails(it)
                })
            } else {
                viewModel.getFallenMeteorProperties()
            }

            binding.swipeRefresh.isRefreshing = false
        }

        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, {
            if (it?.geolocation != null) {
                this.findNavController().navigate(HomeFragmentDirections.actionOpenMeteorFragment(it))
                viewModel.displayPropertyDetailsComplete()
            }
        })

        // Menu icon
        requireActivity().toolbar.setNavigationIcon(R.drawable.ic_menu_icon)
        requireActivity().toolbar.menu.setGroupVisible(0, true)

        return binding.root
    }
}