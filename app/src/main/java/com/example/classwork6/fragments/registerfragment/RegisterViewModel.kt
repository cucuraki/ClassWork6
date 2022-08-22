package com.example.classwork6.fragments.registerfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.classwork6.data.models.LoginRequestModel
import com.example.classwork6.data.models.RegisterResponseModel
import com.example.classwork6.enums.InputEnum
import com.example.classwork6.network.retrofitclient.RetrofitClient
import com.example.classwork6.responsestate.ResponseState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.lang.Exception

class RegisterViewModel : ViewModel() {
    private val _processState = MutableStateFlow<ResponseState<RegisterResponseModel>>(
        ResponseState.Success(
            RegisterResponseModel(-1, "")
        )
    )
    private var _email = ""
    private var _password = ""
    val email get() = _email
    val password get() = _password
    val processState = _processState.asStateFlow()

    fun register(email: String, password: String) {
        viewModelScope.launch {
            registerFlow(email, password).collect {
                _processState.value = it
            }
        }
    }


    private fun registerFlow(email: String, password: String) =
        flow {
            emit(ResponseState.Load())
            val check = check(email, password)
            if (check != InputEnum.ALL_IS_OK) {
                emit(ResponseState.Error(check.toString()))
            } else {
                try {
                    val response =
                        RetrofitClient.registerApi.registerUser(LoginRequestModel(email, password))
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body == null)
                            emit(ResponseState.Error("Error"))
                        else {
                            _email = email
                            _password = password
                            emit(ResponseState.Success(body))
                        }

                    } else {
                        emit(ResponseState.Error(response.errorBody()?.string() ?: "Error"))
                    }
                } catch (e: Exception) {
                    emit(ResponseState.Error(e.message.toString()))
                }
            }
        }


    private fun check(email: String, password: String): InputEnum =
        when {
            //i don't check for username since it is not needed at the moment
            email.isEmpty() -> InputEnum.EMAIL_IS_EMPTY
            password.isEmpty() -> InputEnum.PASSWORD_IS_EMPTY
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                .matches() -> InputEnum.EMAIL_DOES_NOT_MATCH
            else -> InputEnum.ALL_IS_OK
        }
}