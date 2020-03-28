package com.sales.g10.db.repository

import android.app.Application
import com.sales.g10.db.GtenDatabase
import com.sales.g10.db.LoginDetails
import com.sales.g10.db.LoginDetailsDao

class LoginRepository(application: Application) {
    private var loginDetailsDao: LoginDetailsDao

    init {
        val database: GtenDatabase = GtenDatabase.getDatabase(
            application.applicationContext
        )
        loginDetailsDao = database.loginDetailsDao()
    }

    fun insert(loginDetails: LoginDetails): Long? {
        return loginDetailsDao.insertLoginDetail(loginDetails)
    }

    fun getPassword(gUsername:String): String? {
        return loginDetailsDao.getUserNamePassword(gUsername)
    }



}