package com.decagon.edconnect.domain.model

data class DomainUser (
    val firstName: String,
    val lastName: String,
    val password: String,
    val email: String,
    val matricNumber: String,
    val program: String,
    val graduationYear: String,
)