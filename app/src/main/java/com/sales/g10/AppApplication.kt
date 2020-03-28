package com.sales.g10

import android.app.Application
import android.content.Context
import com.sales.g10.services.ApiClient

class AppApplication : Application() {
    companion object {

        private var appContext: Context? = null
        fun getApiClient(): ApiClient {
            return ApiClient()
        }

    }

    override fun onCreate() {
        super.onCreate()

        appContext = applicationContext
        getApiClient()
    }

}