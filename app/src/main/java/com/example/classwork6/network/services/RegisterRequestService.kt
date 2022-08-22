package com.example.classwork6.network.services

import com.example.classwork6.data.models.LoginRequestModel
import com.example.classwork6.data.models.RegisterResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterRequestService {
    @POST("register")
    suspend fun registerUser(@Body body: LoginRequestModel): Response<RegisterResponseModel>
}