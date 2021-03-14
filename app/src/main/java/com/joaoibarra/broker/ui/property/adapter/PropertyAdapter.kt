package com.joaoibarra.broker.ui.property.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.joaoibarra.broker.data.db.property.Property
import com.joaoibarra.broker.data.db.property.PropertyType
import com.joaoibarra.broker.databinding.ItemAreaPropertyBinding
import com.joaoibarra.broker.databinding.ItemHighlightPropertyBinding
import com.joaoibarra.broker.databinding.ItemPropertyBinding
import com.joaoibarra.broker.ui.property.PropertyListViewModel

class PropertyAdapter (
    private val propertyListViewModel: PropertyListViewModel
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mDiffer = AsyncListDiffer(this, DiffCallBack)

    fun submitList(list: List<Property>) {
        mDiffer.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(val propertyType = PropertyType.values()[viewType]) {
            PropertyType.HighlightedProperty -> {
                HighlightedPropertyViewHolder(
                        ItemHighlightPropertyBinding.inflate(
                                LayoutInflater.from(parent.context), parent, false
                        ).also {
                            it.vm = propertyListViewModel
                        }
                )
            }
            PropertyType.Property -> {
                PropertyViewHolder(
                        ItemPropertyBinding.inflate(
                                LayoutInflater.from(parent.context), parent, false
                        ).also {
                            it.vm = propertyListViewModel
                        }
                )
            }
            PropertyType.Area -> {
                AreaPropertyViewHolder(
                        ItemAreaPropertyBinding.inflate(
                                LayoutInflater.from(parent.context), parent, false
                        ).also {
                            it.vm = propertyListViewModel
                        }
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        mDiffer.currentList[position].let {
            when(holder) {
                is HighlightedPropertyViewHolder -> {
                    holder.bind(it)
                }
                is PropertyViewHolder -> {
                    holder.bind(it)
                }
                is AreaPropertyViewHolder -> {
                    holder.bind(it)
                }
            }
        }
    }

    override fun getItemCount() = mDiffer.currentList.size

    override fun getItemViewType(position: Int): Int =
            mDiffer.currentList[position]?.type?.ordinal ?: PropertyType.Property.ordinal

    object DiffCallBack: DiffUtil.ItemCallback<Property>() {
        override fun areItemsTheSame(oldItemPosition: Property, newItemPosition: Property): Boolean {
            return oldItemPosition.id == newItemPosition.id
        }

        override fun areContentsTheSame(oldItemPosition: Property, newItemPosition: Property): Boolean {
            return oldItemPosition.equals(newItemPosition)
        }
    }

}