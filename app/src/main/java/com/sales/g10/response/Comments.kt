package com.sales.g10.response

data class Comments(
    val album_cover: Any?,
    val author: String?,
    val author_id: Int?,
    val children: List<Any>?,
    val comment: String?,
    val datetime: Int?,
    val deleted: Boolean?,
    val downs: Int?,
    val has_admin_badge: Boolean?,
    val id: Int?,
    val image_id: String?,
    val on_album: Boolean?,
    val parent_id: Int?,
    val platform: String?,
    val points: Int?,
    val ups: Int?,
    val vote: Any?
)