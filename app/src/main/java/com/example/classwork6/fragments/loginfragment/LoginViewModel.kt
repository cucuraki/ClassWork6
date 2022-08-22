package com.example.classwork6.fragments.loginfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.classwork6.data.models.LoginResponseModel
import com.example.classwork6.responsestate.ResponseState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.lang.Exception

class LoginViewModel : ViewModel() {
    private val _processState = MutableStateFlow<ResponseState<LoginResponseModel>>(
        ResponseState.Success(
            LoginResponseModel("")
        )
    )
    val processState = _processState.asStateFlow()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            loginFlow(email,password).collect{

            }
        }
    }


    private fun loginFlow(email: String, password: String) =
        flow {
            
        }
}