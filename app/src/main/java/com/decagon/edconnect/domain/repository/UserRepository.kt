package com.decagon.edconnect.domain.repository

import com.decagon.edconnect.domain.model.DomainUser
import com.decagon.edconnect.utils.GenericResponseClass
import com.decagon.edconnect.utils.Resource
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getProfile(id: String): Flow<Resource<DomainUser>>
    suspend fun updateProfile(id: String, user: DomainUser): Flow<Resource<DomainUser>>
    suspend fun logout()
}