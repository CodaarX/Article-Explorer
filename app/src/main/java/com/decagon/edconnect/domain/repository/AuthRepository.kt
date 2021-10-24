package com.decagon.edconnect.domain.repository

import com.decagon.edconnect.domain.model.DomainUser
import com.decagon.edconnect.utils.GenericResponseClass
import com.decagon.edconnect.utils.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun registerUser(user: DomainUser): Resource<GenericResponseClass<String>>
    suspend fun login(email: String, password: String) : Flow<Resource<DomainUser>>
    suspend fun loginUserWithGoogle(): Flow<Resource<DomainUser>>
    suspend fun loginUserWithFacebook(): Flow<Resource<DomainUser>>
    suspend fun requestPasswordChange(email: String): Resource<GenericResponseClass<String>>
    suspend fun changePassword(oldPassword: String, newPassword: String, confirmNewPassword: String) : Resource<GenericResponseClass<String>>
    suspend fun resetPassword(token: String, password: String, confirmPassword: String) : Resource<GenericResponseClass<String>>
}