package com.example.presentation.viewmodel

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.Login
import com.example.movies.R

class LoginViewModel : ViewModel() {

    private val loginModel = Login(R.string.username, R.string.password)

    var userName = ""
    var password = ""

    private val _eventIsSuccess = MutableLiveData<Boolean>()
    val eventIsSuccess: LiveData<Boolean>
        get() = _eventIsSuccess

    private fun isValidUserName(): Boolean {
        if (userName.isBlank() && userName != Resources.getSystem()
                .getString(loginModel.userName)
        ) {
            return false
        }
        return true
    }

    private fun isValidPassword(): Boolean {
        if (password.isBlank() && password != Resources.getSystem()
                .getString(loginModel.password)
        ) {
            _eventIsSuccess.value = false
            return false
        }
        return true
    }

    fun login() {
        if (isValidPassword() && isValidUserName()) {
            _eventIsSuccess.value = true
        }
    }


}