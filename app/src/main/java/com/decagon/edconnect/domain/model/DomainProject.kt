package com.decagon.edconnect.domain.model

data class DomainProject (
    val name: String,
    val abstract: String,
    val authors: List<String>,
    val tags: List<String>
)