package com.decagon.edconnect.data.datasources.localSource.daos

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.decagon.edconnect.data.datasources.localSource.model.CacheMyProjects

interface MyProjectsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProject(project: CacheMyProjects)

    @Transaction
    @Query("SELECT * FROM my_created_project_table")
    fun getAllProjects(): List<CacheMyProjects>


    @Query("DELETE FROM my_created_project_table")
    fun deleteAllMyProjects()

}