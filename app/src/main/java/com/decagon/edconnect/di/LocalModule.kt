package com.decagon.edconnect.di

import android.content.Context
import androidx.room.Room
import com.decagon.edconnect.data.datasources.localSource.EdConnectDatabase
import com.decagon.edconnect.data.datasources.localSource.EdConnectDatabase.Companion.DATABASE_NAME
import com.decagon.edconnect.data.datasources.localSource.daos.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun provideApplicationDatabase(@ApplicationContext context : Context): EdConnectDatabase {
        return Room.databaseBuilder(context, EdConnectDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideFavouriteDao(edConnectDatabase: EdConnectDatabase): FavouriteProjectsDao {
        return edConnectDatabase.favouriteProjectDao()
    }

    @Singleton
    @Provides
    fun providesUserDao(edConnectDatabase: EdConnectDatabase): UserDao {
        return edConnectDatabase.userDao()
    }

    @Singleton
    @Provides
    fun providesProjectDao(edConnectDatabase: EdConnectDatabase): ProjectDao {
        return edConnectDatabase.projectDao()
    }

    @Singleton
    @Provides
    fun searchProjectsDao(edConnectDatabase: EdConnectDatabase): SearchProjectsDao {
        return edConnectDatabase.searchProjectsDao()
    }

    @Singleton
    @Provides
    fun myProjectsDao(edConnectDatabase: EdConnectDatabase): MyProjectsDao {
        return edConnectDatabase.myProjectsDao()
    }

}
