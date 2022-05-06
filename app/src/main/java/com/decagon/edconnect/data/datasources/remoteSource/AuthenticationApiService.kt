package com.decagon.edconnect.data.datasources.remoteSource

import com.decagon.edconnect.data.datasources.remoteSource.model.ApiUser
import com.decagon.edconnect.domain.model.DomainAuthenticatedUser
import com.decagon.edconnect.utils.GenericResponseClass
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthenticationApiService {
    // register user
    @POST("/api/user/signup")
    suspend fun registerUser(@Body user: ApiUser): GenericResponseClass<String>

    @POST("api/user/login")
    suspend fun login(@Body email: String, @Body password: String): GenericResponseClass<DomainAuthenticatedUser<ApiUser>>

    @POST("auth/google")
    suspend fun loginUserWithGoogle(): GenericResponseClass<DomainAuthenticatedUser<ApiUser>>

    @POST("auth/facebook")
    suspend fun loginUserWithFacebook(): GenericResponseClass<DomainAuthenticatedUser<ApiUser>>

    @POST("api/password/email/send")
    suspend fun requestPasswordChange(@Body email: String): GenericResponseClass<String>

    @POST("auth/password/change")
    suspend fun changePassword(@Body password: String, @Body newPassword: String, @Body confirmNewPassword: String): GenericResponseClass<String>

    @POST("api/password/reset/{token}")
    suspend fun resetPassword(
        @Path("token") token: String,
        @Body password: String,
        @Body confirmPassword: String
    ): GenericResponseClass<String>
}
