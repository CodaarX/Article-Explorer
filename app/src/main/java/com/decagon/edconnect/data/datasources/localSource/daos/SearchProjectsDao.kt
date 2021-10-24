package com.decagon.edconnect.data.datasources.localSource.daos

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.decagon.edconnect.data.datasources.localSource.model.CacheProject
import com.decagon.edconnect.data.datasources.localSource.model.CacheSearchProjects

interface SearchProjectsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProject(project: CacheSearchProjects)

    @Transaction
    @Query("SELECT * FROM search_project_result_table WHERE name = :name")
    fun getAllProjects(name: String): List<CacheProject>


    @Query("DELETE FROM search_project_result_table")
    fun deleteAllSearchedProjects()
}