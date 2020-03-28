package com.sales.g10.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sales.g10.R
import com.sales.g10.response.Comments
import kotlinx.android.synthetic.main.recyclerview_message_list.view.*

class CommentsAdapter(
    private val arrayList: ArrayList<Comments>
) :
    RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(
            R.layout.recyclerview_message_list, viewGroup,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(arrayList[position], position)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(result: Comments, position: Int) {
            itemView.txtMessage.text = result.comment

        }
    }
}