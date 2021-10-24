package com.decagon.edconnect.utils

data class UiStateManager<T>(
    val isLoading: Boolean = false,
    val data: T? = null,
    val error: String = ""
)