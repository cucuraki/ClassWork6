package com.example.classwork6.fragments.loginfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.classwork6.data.models.LoginRequestModel
import com.example.classwork6.data.models.LoginResponseModel
import com.example.classwork6.datastore.DataStore
import com.example.classwork6.enums.InputEnum
import com.example.classwork6.network.retrofitclient.RetrofitClient
import com.example.classwork6.responsestate.ResponseState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

class LoginViewModel : ViewModel() {
    private val _processState = MutableStateFlow<ResponseState<LoginResponseModel>>(
        ResponseState.Success(
            LoginResponseModel("")
        )
    )
    val processState = _processState.asStateFlow()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            loginFlow(email, password).collect {
                _processState.value = it
            }
        }
    }


    private fun loginFlow(email: String, password: String) =
        flow {
            emit(ResponseState.Load())
            val check = check(email, password)
            if (check != InputEnum.ALL_IS_OK) {
                emit(ResponseState.Error(check.toString()))
                return@flow
            }
            try {
                val response = RetrofitClient.loginApi.login(LoginRequestModel(email, password))
                if (response.isSuccessful) {
                    DataStore.save("email", email)
                    DataStore.save("password", password)
                    emit(ResponseState.Success(response.body() ?: LoginResponseModel("")))
                } else {
                    emit(ResponseState.Error(response.errorBody().toString()))
                }

            } catch (e: Exception) {
                emit(ResponseState.Error(e.message.toString()))
            }
        }

    private fun check(email: String, password: String): InputEnum =
        when {
            email.isEmpty() -> InputEnum.EMAIL_IS_EMPTY
            password.isEmpty() -> InputEnum.PASSWORD_IS_EMPTY
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                .matches() -> InputEnum.EMAIL_DOES_NOT_MATCH
            else -> InputEnum.ALL_IS_OK
        }
}