package com.sales.g10.services

import ResponseGArrayBody
import ResponseGBody
import SendComment
import com.sales.g10.utils.Constant
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import org.jetbrains.annotations.NotNull
import retrofit2.Call
import retrofit2.http.*


interface ApiInterface {

    @GET(Constant.SubUrl.GET_IMAGE)
    fun getImage(@Path("imageId") imageId:String, @Header("Authorization") auth:String): Call<ResponseGBody>

    @GET(Constant.SubUrl.GET_COMMENTS)
    fun getComments(@Path("imageId") imageId:String,@Header("Authorization") auth:String): Call<ResponseGArrayBody>

    @Multipart
    @POST(Constant.SubUrl.SEND_COMMENT)
    fun uploadImages(
        @Path("imageId") imageId:String,
        @Part file: MultipartBody.Part,
        @Header("Authorization") auth:String
    ): Call<ResponseGBody>
}