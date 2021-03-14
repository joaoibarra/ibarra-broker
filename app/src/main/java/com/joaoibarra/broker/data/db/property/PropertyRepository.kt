package com.joaoibarra.broker.data.db.property

import com.joaoibarra.broker.data.remote.BrokerApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class PropertyRepository (
    private val brokerApi: BrokerApi,
    private val propertyDao: PropertyDao
) {
    fun getProperties() = flow<List<Property>> {
        if(propertyDao.getCount() > 0) {
            emit(propertyDao.getAll())
        } else {
            emit(getRemoteProperties())
        }
    }.flowOn(Dispatchers.IO)

    private suspend fun getRemoteProperties(): List<Property> = withContext(Dispatchers.IO) {
        val result = brokerApi.getProperties()
        if (result.isSuccessful) {
            result.body()?.let {
                propertyDao.insertList(it.toList())
                propertyDao.getAll()
            } ?: emptyList()
        } else{
            emptyList()
        }
    }
}