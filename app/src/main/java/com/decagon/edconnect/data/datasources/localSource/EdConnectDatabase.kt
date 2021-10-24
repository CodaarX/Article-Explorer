package com.decagon.edconnect.data.datasources.localSource

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.decagon.edconnect.data.datasources.localSource.daos.*
import com.decagon.edconnect.data.datasources.localSource.model.*

@Database(
    entities = [CacheFavouriteProject::class, CacheProject::class, CacheUser::class, CacheMyProjects::class, CacheSearchProjects::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(TypeConverter::class)
abstract class EdConnectDatabase : RoomDatabase() {
    abstract fun projectDao(): ProjectDao
    abstract fun userDao(): UserDao
    abstract fun favouriteProjectDao(): FavouriteProjectsDao
    abstract fun myProjectsDao(): MyProjectsDao
    abstract fun searchProjectsDao() : SearchProjectsDao

    companion object {
        var DATABASE_NAME: String = "ed_connect db"
    }
}