package com.github.paulajcm.devices.presentation.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.github.paulajcm.devices.R
import com.github.paulajcm.devices.databinding.FragmentHomeBinding
import com.github.paulajcm.devices.domain.entities.Device
import com.github.paulajcm.devices.presentation.UIState
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var devicesAdapter: DeviceListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupDeviceList()
        setupSearch()

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupState()
        homeViewModel.retrieveDevices()
    }

    private fun setupState() {
        homeViewModel.devicesState.observe(viewLifecycleOwner) { state ->
            when(state) {
                is UIState.Loaded<*> -> showDeviceList(state.value as? List<Device>)
                is UIState.Loading-> if(state.isLoading) showProgress()
                is UIState.Error -> showError()
                is UIState.Empty -> showEmpty()
            }
        }    }

    private fun setupSearch() {
        binding.editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // Do nothing
            }

            override fun afterTextChanged(p0: Editable?) {
                // Do nothing
            }

            override fun onTextChanged(query: CharSequence?, p1: Int, p2: Int, p3: Int) {
                homeViewModel.retrieveDevices(binding.editTextSearch.text.toString())
            }
        })
    }

    private fun setupDeviceList() {
        devicesAdapter = DeviceListAdapter { device ->
            onDeviceClicked(device)
        }
        with(binding.includeDeviceList.recyclerView){
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = devicesAdapter
        }
    }

    private fun showEmpty() {
        binding.includeDeviceList.textViewEmpty.text = getString(R.string.empty_message)
        binding.includeDeviceList.groupEmptyViews.visibility = View.VISIBLE
        binding.includeDeviceList.recyclerView.visibility = View.GONE
    }

    private fun showError() {
        binding.includeDeviceList.textViewEmpty.text = getString(R.string.error_message)
        binding.includeDeviceList.groupEmptyViews.visibility = View.VISIBLE
        binding.includeDeviceList.recyclerView.visibility = View.GONE
    }

    private fun showProgress() {
        binding.includeDeviceList.textViewEmpty.text = getString(R.string.loading_message)
        binding.includeDeviceList.groupEmptyViews.visibility = View.VISIBLE
        binding.includeDeviceList.recyclerView.visibility = View.GONE
    }

    private fun showDeviceList(devices: List<Device>?) {
        devices?.let {
            devicesAdapter.submitList(it)
        }
        binding.includeDeviceList.groupEmptyViews.visibility = View.GONE
        binding.includeDeviceList.recyclerView.visibility = View.VISIBLE
    }

    private fun onDeviceClicked(device: Device) {
        val direction = HomeFragmentDirections.actionNavigationHomeToNavigationDeviceDetails(device)
        findNavController().navigate(direction)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}