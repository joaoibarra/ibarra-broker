package com.joaoibarra.broker.ui.property.adapter

import androidx.recyclerview.widget.RecyclerView
import com.joaoibarra.broker.data.db.property.Property
import com.joaoibarra.broker.databinding.ItemPropertyBinding

class PropertyViewHolder(private val binding: ItemPropertyBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(property: Property) {
        binding.apply {
            this.property = property
            executePendingBindings()
        }
    }
}