package com.github.paulajcm.devices.presentation.device_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.github.paulajcm.devices.databinding.FragmentDeviceDetailsBinding
import com.github.paulajcm.devices.databinding.FragmentSettingsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DeviceDetailsFragment : Fragment() {

    private val deviceDetailsViewModel: DeviceDetailsViewModel by viewModel()
    private var _binding: FragmentDeviceDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDeviceDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}