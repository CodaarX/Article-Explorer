package com.decagon.edconnect.data.datasources.localSource.model

import androidx.room.Entity


@Entity(tableName = "user_table")
data class CacheUser (
    val firstName: String,
    val lastName: String,
    val password: String,
    val email: String,
    val matricNumber: String,
    val program: String,
    val graduationYear: String,
        )