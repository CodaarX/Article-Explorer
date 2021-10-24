package com.decagon.edconnect.data.datasources.localSource.daos

import androidx.room.*
import com.decagon.edconnect.data.datasources.localSource.model.CacheProject
import kotlinx.coroutines.flow.Flow

interface ProjectDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProject(project: CacheProject)

    @Transaction
    @Query("SELECT * FROM project_table")
    fun getAllProjects(): List<CacheProject>

    @Query("SELECT * FROM project_table WHERE fileId = :fileId")
    fun getSingleProject(fileId: String) : CacheProject

    @Query("DELETE FROM user_table")
    fun deleteAllMyProjects()
}