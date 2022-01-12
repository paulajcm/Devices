package com.github.paulajcm.devices.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.paulajcm.devices.databinding.DeviceListItemBinding
import com.github.paulajcm.devices.domain.entities.Device

class DeviceListAdapter(
    private val click: (Device) -> Unit
) : ListAdapter<Device, DeviceListAdapter.DeviceViewHolder>(Diff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        return DeviceViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        holder.bind(getItem(position), click)
    }

    class DeviceViewHolder(private val binding: DeviceListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Device, openDetail: ((Device) -> Unit)) {
            with(binding) {
                root.setOnClickListener { openDetail.invoke(data) }
                textViewItemTitle.text = data.title
                textViewItemCurrency.text = data.currency
                textViewItemPrice.text = data.price.toString()
            }
        }

        companion object {
            fun from(parent: ViewGroup): DeviceViewHolder {
                val binding = DeviceListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return DeviceViewHolder(binding)
            }
        }
    }

    private class Diff : DiffUtil.ItemCallback<Device>() {
        override fun areItemsTheSame(oldItem: Device, newItem: Device): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Device, newItem: Device): Boolean {
            return oldItem == newItem
        }
    }
}
