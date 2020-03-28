package com.sales.g10.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog

object Constant {
    const val NO_INTERNET = "No Internet, Please check your network connection."
    var IS_NETWORK_AVAILABLE: Boolean = true
    const val SERVER_NOT_RESPONDING = "Server not responding"

    const val JAVA_NET_EXCEPTION = "java.net.ConnectException"

    const val AUTHORIZATION = "Client-ID dda39a8297e4684"

    const val BEARER="Bearer 695c25981b46ab8c8945dc43ae03d07182cc834d"

    object SubUrl {
        const val GET_IMAGE = "image/{imageId}"

        const val GET_COMMENTS = "{imageId}/comments/best"

        const val SEND_COMMENT = "{imageId}/comment"
    }

    /**
     * This method is for showing Alert OR Error Dialog message of API Response
     *
     * @param context
     * @param title
     * @param msg
     */
    fun alertDialog(context: Context?, title: String, msg: String) {
        try {
            val dialogBuilder = AlertDialog.Builder(context!!)
            //val inflater = context.layoutInflater
            dialogBuilder.setCancelable(false)
            //val dialogView = inflater.inflate(R.layout.custom_alert_dialog, null)
            //dialogBuilder.setView(dialogView)

            //dialogBuilder.setTitle("Custom dialog")
            dialogBuilder.setMessage(msg)
            dialogBuilder.setPositiveButton("Ok", { dialog, whichButton ->
                dialog.dismiss()
            })

            val b = dialogBuilder.create()
            b.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}