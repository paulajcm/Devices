package com.github.paulajcm.devices.presentation.user_devices

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.github.paulajcm.devices.databinding.FragmentUserDevicesBinding

class UserDevicesFragment : Fragment() {

    private lateinit var userDevicesViewModel: UserDevicesViewModel
    private var _binding: FragmentUserDevicesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userDevicesViewModel =
            ViewModelProvider(this).get(UserDevicesViewModel::class.java)

        _binding = FragmentUserDevicesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        userDevicesViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}