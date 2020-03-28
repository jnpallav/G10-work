package com.sales.g10.fragment

import ResponseGArrayBody
import ResponseGBody
import SendComment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sales.g10.R
import com.sales.g10.adapter.CommentsAdapter
import com.sales.g10.response.Comments
import com.sales.g10.response.ErrorBodyG
import com.sales.g10.response.ImageBody
import com.sales.g10.services.interactor.GalleryInteractor
import com.sales.g10.services.presenter.GalleryPresenter
import com.sales.g10.services.view.GalleryView
import com.sales.g10.utils.Constant
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_image.*
import kotlinx.android.synthetic.main.progress_bar.*

/**
 * A simple [Fragment] subclass.
 * Use the [ImageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ImageFragment : Fragment(), GalleryView {
    private lateinit var galleryPresenter: GalleryPresenter
    private lateinit var commentsAdapter: CommentsAdapter
    private var commentList: ArrayList<Comments>? = null
    private var imageId: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        galleryPresenter = GalleryInteractor(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnSearch.setOnClickListener {

            if (!edImageSearch.text.isNullOrEmpty()) {
                imageId = edImageSearch.text.toString().trim()
                galleryPresenter.getImageGallery(
                    imageId!!,
                    Constant.AUTHORIZATION
                )
            } else {
                imageId =null
                Toast.makeText(activity, "Enter image id", Toast.LENGTH_SHORT).show()
            }
            hideKeyboard(it)
        }

        btnComment.setOnClickListener {
            if(!edComment.text.isNullOrEmpty()) {
                galleryPresenter.sendComments(
                    imageId!!,SendComment(edComment.text.toString().trim()),
                    Constant.BEARER
                )
            } else {
                Toast.makeText(activity, "Enter comments", Toast.LENGTH_SHORT).show()
            }
            hideKeyboard(it)
        }

        commentList = ArrayList()
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = RecyclerView.VERTICAL

        val verticalDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        val verticalDivider = ContextCompat.getDrawable(activity!!, R.drawable.vertical_divider)
        verticalDecoration.setDrawable(verticalDivider!!)
        recyclerViewImageComments.addItemDecoration(verticalDecoration)
        recyclerViewImageComments.isNestedScrollingEnabled = false
        recyclerViewImageComments.layoutManager = layoutManager
        commentsAdapter = CommentsAdapter(commentList!!)
        recyclerViewImageComments.adapter = commentsAdapter
    }

    override fun showProgress() {
        if (progress_bar != null) {
            progress_bar.visibility = View.VISIBLE
        }
    }

    override fun hideProgress() {
        if (progress_bar != null) {
            progress_bar.visibility = View.GONE
        }
    }

    override fun onFailure(message: String) {
        if (activity != null) {
            group.visibility = View.GONE
            imageId = null
            imgView.setImageResource(R.drawable.no_image)
            Constant.alertDialog(activity!!, "", msg = message)
        }
    }

    override fun onResponseSendComment(responseGBody: ResponseGBody) {

        try {
            if (responseGBody.success!!)
            {
                edComment.setText("")
                getComments()
            }
            else
            {
                val type = object : TypeToken<ErrorBodyG>() {}.type
                val l = Gson().toJson(responseGBody.data)
                val errorBody = Gson().fromJson<ErrorBodyG>(l, type)
                Constant.alertDialog(activity!!, "", errorBody.error!!)
            }
        }
        catch (ex:Exception)
        {
            ex.printStackTrace()

        }

    }

    override fun onResponseGetComment(responseGArrayBody: ResponseGArrayBody) {

        try {

            commentList!!.clear()
            val type = object : TypeToken<ArrayList<Comments>>() {}.type
            val l = Gson().toJson(responseGArrayBody.data)
            val messageList = Gson().fromJson<ArrayList<Comments>>(l, type)
            if (messageList != null && messageList.isNotEmpty()) {
                commentList!!.addAll(messageList)
            }
            commentsAdapter.notifyDataSetChanged()

        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    override fun onResponseGetImage(responseGBody: ResponseGBody) {
        try {
            val type = object : TypeToken<ImageBody>() {}.type
            val l = Gson().toJson(responseGBody.data)
            val imageBody = Gson().fromJson<ImageBody>(l, type)
            Picasso.get()
                .load(imageBody.link)
                .placeholder(R.drawable.loading_image)
                .error(R.drawable.no_image)
                .into(imgView)

            getComments()
            group.visibility = View.VISIBLE

        } catch (ex: Exception) {
            ex.printStackTrace()
        }

    }

    private fun getComments()
    {
        galleryPresenter.getImageComments(
            imageId!!,
            Constant.AUTHORIZATION
        )
    }

    private fun hideKeyboard(view: View)
    {
        val imm =activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}
