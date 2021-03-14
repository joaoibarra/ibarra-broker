package com.joaoibarra.broker.ui.property

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.joaoibarra.broker.databinding.FragmentPropertyListBinding
import com.joaoibarra.broker.ui.property.adapter.PropertyAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class PropertyListFragment: Fragment() {
    private val propertyListViewModel: PropertyListViewModel by viewModel()

    private var binding: FragmentPropertyListBinding? = null
    private val propertyAdapter: PropertyAdapter by lazy {
        PropertyAdapter(propertyListViewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentPropertyListBinding.inflate(inflater, container, false)
        .apply {
            lifecycleOwner = this@PropertyListFragment
            vm = propertyListViewModel
            binding = this
            binding?.adapter = propertyAdapter
        }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setObserver()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
        propertyListViewModel.properties.removeObservers(requireActivity())
    }

    private fun setObserver() {
        propertyListViewModel.properties.observe(viewLifecycleOwner) { list ->
            propertyAdapter.run {
                submitList(list)
                notifyDataSetChanged()
            }
        }
    }
}