package com.danielvilha.fallenmeteors.ui.meteor

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.danielvilha.fallenmeteors.R
import com.danielvilha.fallenmeteors.databinding.FragmentMeteorBinding
import com.google.android.gms.maps.SupportMapFragment
import kotlinx.android.synthetic.main.app_bar_main.*

class MeteorFragment : Fragment() {

    private lateinit var binding: FragmentMeteorBinding

    /**
     * Lazily initialize our [MeteorViewModel]
     */
    private val viewModel: MeteorViewModel by lazy {
        ViewModelProvider(this).get(MeteorViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // This is my application of my activity
        val application = requireNotNull(activity).application

        // Get a reference to the binding object and inflate the fragment views.
        binding = FragmentMeteorBinding.inflate(inflater)

        // I get this bundle from the last argument
        val property = MeteorFragmentArgs.fromBundle(requireArguments()).property

        // Creating the View Model Factory
        val viewModelFactory = MeteorViewModelFactory(property, application)
        binding.viewModel = ViewModelProvider(
            this, viewModelFactory).get(MeteorViewModel::class.java)

        // Menu change navigation icon
        requireActivity().toolbar.setNavigationIcon(R.drawable.ic_return_icon)
        requireActivity().toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.lifecycleOwner = this
        return binding.root
    }

    /**
     * This is for me to set the map view
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(viewModel.callback)
    }
}