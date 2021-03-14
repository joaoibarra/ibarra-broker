package com.joaoibarra.broker.ui.property

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joaoibarra.broker.data.db.property.Property
import com.joaoibarra.broker.data.db.property.PropertyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PropertyListViewModel (
    private val repository: PropertyRepository
): ViewModel() {
    private val _properties = MutableLiveData<List<Property>>()
    val properties: LiveData<List<Property>> = _properties


    init {
        fetchProperties()
    }

    fun fetchProperties() {
        viewModelScope.launch (Dispatchers.IO) {
            repository.getProperties().collect { _properties.postValue(it) }
        }
    }
}