package com.sales.g10

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.sales.g10.db.LoginDetails
import com.sales.g10.db.repository.LoginRepository

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: LoginRepository = LoginRepository(application)

    fun insert(loginDetails: LoginDetails): Long? {
        return repository.insert(loginDetails)
    }

    fun getPassword(userName:String): String?
    {
        return repository.getPassword(userName)
    }
}