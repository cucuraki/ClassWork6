package com.example.classwork6.network.services

import com.example.classwork6.data.models.LoginRequestModel
import com.example.classwork6.data.models.LoginResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginRequestService {
    @POST("login")
    suspend fun login(@Body body: LoginRequestModel): Response<LoginResponseModel>
}