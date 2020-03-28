package com.sales.g10.services.presenter

import SendComment

interface GalleryPresenter {

    fun getImageGallery(imageId:String,authorization:String)

    fun getImageComments(imageId:String,authorization:String)

    fun sendComments(imageId:String,sendComment: SendComment,authorization:String)
}