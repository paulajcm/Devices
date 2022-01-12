package com.github.paulajcm.devices.presentation.device_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.github.paulajcm.devices.databinding.FragmentDeviceDetailsBinding

class DeviceDetailsFragment : Fragment() {

    private var _binding: FragmentDeviceDetailsBinding? = null

    private val binding get() = _binding!!

    private val args: DeviceDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDeviceDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val device = args.device
        binding.textViewDeviceDetailTitle.text = device.title
        binding.textViewDeviceType.text = device.type
        binding.textViewDeviceDetailPrice.text = "${device.currency} ${device.price}"
        binding.textViewDeviceDetailDescription.text = device.description
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}