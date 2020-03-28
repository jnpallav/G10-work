package com.sales.g10.response

data class ErrorBodyG(
    val error: String? = null,
    val method: String? = null,
    val request: String? = null
)