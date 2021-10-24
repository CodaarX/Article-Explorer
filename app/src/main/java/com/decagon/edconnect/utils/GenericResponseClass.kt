package com.decagon.edconnect.utils

data class GenericResponseClass<T>(
    val message: String,
    val payload: T,
    val status: Int
)
