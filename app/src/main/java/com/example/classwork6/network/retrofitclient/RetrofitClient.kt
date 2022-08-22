package com.example.classwork6.network.retrofitclient

import com.example.classwork6.network.services.LoginRequestService
import com.example.classwork6.network.services.RegisterRequestService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://reqres.in/api/"
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val retrofitBuilder by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                MoshiConverterFactory
                    .create(moshi)
            )
            .build()
    }

    val loginApi by lazy{
        retrofitBuilder.create(LoginRequestService::class.java)
    }
    val registerApi by lazy {
        retrofitBuilder.create(RegisterRequestService::class.java)
    }

}