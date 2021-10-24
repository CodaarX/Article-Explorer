package com.decagon.edconnect.data.datasources.localSource.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_project_result_table")
data class CacheSearchProjects (
    @PrimaryKey
    override var fileId: String? = null,
    override var name: String,
    override var abstract: String,
    override var authors: List<String>,
    override var tags: List<String>
): CacheProject(fileId, name, abstract, authors, tags)