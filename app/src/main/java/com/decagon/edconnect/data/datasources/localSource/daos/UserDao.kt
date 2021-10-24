package com.decagon.edconnect.data.datasources.localSource.daos

import androidx.room.*
import com.decagon.edconnect.data.datasources.localSource.model.CacheUser
import kotlinx.coroutines.flow.Flow

interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: CacheUser)

    @Transaction
    @Query("SELECT * FROM user_table")
    fun getUser(): CacheUser

    @Query("DELETE FROM user_table")
    fun deleteUser()
}