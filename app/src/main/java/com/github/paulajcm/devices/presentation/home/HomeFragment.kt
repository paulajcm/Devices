package com.github.paulajcm.devices.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.github.paulajcm.devices.databinding.FragmentHomeBinding
import com.github.paulajcm.devices.domain.entities.Device
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupDeviceList()

        return root
    }

    private fun setupDeviceList() {
        with(binding.recyclerViewDevices){
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = DeviceListAdapter { device ->
                onDeviceClicked(device)
            }
        }
    }

    private fun onDeviceClicked(device: Device) {
        
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}