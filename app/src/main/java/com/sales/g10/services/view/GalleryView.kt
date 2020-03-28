package com.sales.g10.services.view

import ResponseGArrayBody
import ResponseGBody

interface GalleryView {

    fun showProgress()

    fun hideProgress()

    fun onFailure(message: String)

    fun onResponseGetImage(responseGBody: ResponseGBody)

    fun onResponseGetComment(responseGArrayBody: ResponseGArrayBody)

    fun onResponseSendComment(responseGBody: ResponseGBody)
}