package com.sales.g10.response

data class ImageBody(
    val account_id: Int?,
    val account_url: String?,
    val ad_config: AdConfig?,
    val ad_type: Int?,
    val ad_url: String?,
    val animated: Boolean?,
    val bandwidth: Int?,
    val comment_count: Int?,
    val datetime: Int?,
    val description: Any?,
    val downs: Int?,
    val edited: Int?,
    val favorite: Boolean?,
    val favorite_count: Int?,
    val has_sound: Boolean?,
    val height: Int?,
    val id: String?,
    val in_gallery: Boolean?,
    val in_most_viral: Boolean?,
    val is_ad: Boolean?,
    val is_album: Boolean?,
    val link: String?,
    val nsfw: Boolean?,
    val points: Int?,
    val score: Int?,
    val section: String?,
    val size: Int?,
    val tags: List<Any?>?,
    val title: String?,
    val topic: String?,
    val topic_id: Int?,
    val type: String?,
    val ups: Int?,
    val views: Int?,
    val vote: Any?,
    val width: Int?
) {
    data class AdConfig(
        val highRiskFlags: List<Any?>?,
        val safeFlags: List<String?>?,
        val showsAds: Boolean?,
        val unsafeFlags: List<String?>?,
        val wallUnsafeFlags: List<Any?>?
    )
}