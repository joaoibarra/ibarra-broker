package com.joaoibarra.broker.data.remote

import com.joaoibarra.broker.data.remote.domain.property.PropertyListResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET

interface BrokerApi {
    @GET("v3/a332277d-0856-43ad-a373-d2cdfdf48465")
    suspend fun getProperties(): Response<PropertyListResponse>

    companion object {
        fun newInstance(retrofit: Retrofit): BrokerApi {
            return retrofit.create(BrokerApi::class.java)
        }
    }
}