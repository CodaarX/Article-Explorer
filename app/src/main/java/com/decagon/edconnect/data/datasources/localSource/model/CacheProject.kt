package com.decagon.edconnect.data.datasources.localSource.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "project_table")
open  class CacheProject (
    @PrimaryKey
    open var fileId: String? = null,
    open var name: String,
    open var abstract: String,
    open var authors: List<String>,
    open var tags: List<String>
        )