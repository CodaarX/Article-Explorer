package com.decagon.edconnect.data.datasources.localSource.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_created_project_table")
data class CacheMyProjects (
    @PrimaryKey
    override var fileId: String? = null,
    override var name: String,
    override var abstract: String,
    override var authors: List<String>,
    override var tags: List<String>
): CacheProject(fileId, name, abstract, authors, tags)