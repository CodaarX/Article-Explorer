package com.decagon.edconnect.data.datasources.remoteSource

import com.decagon.edconnect.data.datasources.remoteSource.model.ApiProject
import com.decagon.edconnect.data.datasources.remoteSource.model.ApiUser
import com.decagon.edconnect.domain.model.DomainUser
import com.decagon.edconnect.utils.GenericResponseClass
import com.decagon.edconnect.utils.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApiService {
    @POST("api/user/profile/get/{id}")
    suspend fun getProfile(
        @Path("id") id: String
    ): GenericResponseClass<ApiUser>

    @POST("api/user/profile/update/{id}")
    suspend fun updateProfile(
        @Path("id") id: String,
        @Body user: ApiUser
    ): GenericResponseClass<ApiUser>

    @POST("api/user/logout")
    suspend fun logout()
}