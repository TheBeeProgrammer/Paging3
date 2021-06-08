package com.example.presentation.viewmodel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.model.Login
import com.example.movies.R

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val loginModel = Login(R.string.username, R.string.password)

    var userName = ""
    var password = ""

    private val _eventIsSuccess = MutableLiveData<Boolean>()
    val eventIsSuccess: LiveData<Boolean>
        get() = _eventIsSuccess

    private fun isValidUserName(): Boolean {
        if (userName.isBlank() || userName != getApplication<Application>().resources.getString(
                loginModel.userName
            )
        ) {
            return false
        }
        return true
    }

    private fun isValidPassword(): Boolean {
        if (password.isBlank() || password != getApplication<Application>().resources.getString(
                loginModel.password
            )
        ) {
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