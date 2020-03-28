package com.sales.g10.services.interactor

import ResponseGArrayBody
import ResponseGBody
import SendComment
import com.sales.g10.AppApplication
import com.sales.g10.services.presenter.GalleryPresenter
import com.sales.g10.services.view.GalleryView
import com.sales.g10.utils.Constant
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GalleryInteractor(private val galleryView: GalleryView) : GalleryPresenter {

    override fun getImageGallery(imageId: String, authorization: String) {
        if (Constant.IS_NETWORK_AVAILABLE) {
            galleryView.showProgress()

            AppApplication.getApiClient().getRestInterface().getImage(imageId,authorization)
                .enqueue(object : Callback<ResponseGBody> {

                    override fun onResponse(call: Call<ResponseGBody>, responseSsc: Response<ResponseGBody>) {
                        galleryView.hideProgress()
                        if (responseSsc.isSuccessful) {
                            galleryView.onResponseGetImage(responseSsc.body()!!)
                        }
                        else
                        {
                            galleryView.onFailure("No Image found")
                        }
                    }

                    override fun onFailure(call: Call<ResponseGBody>, t: Throwable) {
                        galleryView.hideProgress()
                        if (t.cause.toString().contains(Constant.JAVA_NET_EXCEPTION)) {
                            galleryView.onFailure(Constant.SERVER_NOT_RESPONDING)
                        } else {
                            galleryView.onFailure(t.message!!)
                        }


                    }
                })
        } else {
            galleryView.onFailure(Constant.NO_INTERNET)
        }
    }

    override fun getImageComments(imageId: String, authorization: String) {
        if (Constant.IS_NETWORK_AVAILABLE) {
            galleryView.showProgress()

            AppApplication.getApiClient().getRestInterface().getComments(imageId,authorization)
                .enqueue(object : Callback<ResponseGArrayBody> {

                    override fun onResponse(call: Call<ResponseGArrayBody>, responseSsc: Response<ResponseGArrayBody>) {
                        galleryView.hideProgress()
                        if (responseSsc.isSuccessful) {
                            galleryView.onResponseGetComment(responseSsc.body()!!)
                        }
                    }

                    override fun onFailure(call: Call<ResponseGArrayBody>, t: Throwable) {
                        galleryView.hideProgress()
                        if (t.cause.toString().contains(Constant.JAVA_NET_EXCEPTION)) {
                            galleryView.onFailure(Constant.SERVER_NOT_RESPONDING)
                        } else {
                            galleryView.onFailure(t.message!!)
                        }


                    }
                })
        } else {
            galleryView.onFailure(Constant.NO_INTERNET)
        }
    }

    override fun sendComments(imageId: String, sendComment: SendComment, authorization: String) {
        if (Constant.IS_NETWORK_AVAILABLE) {
            galleryView.showProgress()

            //val reqFile = RequestBody.create(MediaType.parse(mimeType), file)
            //val body = MultipartBody.Part.createFormData("prescriptionFiles", file.name, reqFile)
            //val type = mimeType.split("/")
            //val t = type[1]

            val body =
                MultipartBody.Part.createFormData("comment", sendComment.comment!!)

            AppApplication.getApiClient().getRestInterface().uploadImages(imageId,body,authorization)
                .enqueue(object : Callback<ResponseGBody> {
                    override fun onFailure(call: Call<ResponseGBody>, t: Throwable) {
                        galleryView.onFailure(t.message!!)

                    }

                    override fun onResponse(
                        call: Call<ResponseGBody>,
                        response: Response<ResponseGBody>
                    ) {

                        galleryView.onResponseSendComment(response.body()!!)
                    }

                })
        } else {
            galleryView.onFailure(Constant.NO_INTERNET)
        }
    }
}