package com.decagon.edconnect.domain.model

data class DomainAuthenticatedUser<T> (
    val user: T,
    val authorizationToken: String
    )