package com.decagon.edconnect.data.datasources.localSource.daos

import androidx.room.*
import com.decagon.edconnect.data.datasources.localSource.model.CacheFavouriteProject
import com.decagon.edconnect.data.datasources.localSource.model.CacheProject
import kotlinx.coroutines.flow.Flow

interface FavouriteProjectsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavouriteProject(project: CacheFavouriteProject)

    @Transaction
    @Query("SELECT * FROM favourite_project_table")
    fun getAllProjects(): List<CacheProject>

    @Query("DELETE FROM favourite_project_table")
    fun deleteAllProjects()

}