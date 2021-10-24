package com.decagon.edconnect.data.repositoryImplementations

import com.decagon.edconnect.data.datasources.localSource.EdConnectDatabase
import com.decagon.edconnect.data.datasources.localSource.model.mappers.CacheUserMapper
import com.decagon.edconnect.data.datasources.remoteSource.AuthenticationApiService
import com.decagon.edconnect.data.datasources.remoteSource.model.mappers.ApiUserMapper
import com.decagon.edconnect.domain.model.DomainUser
import com.decagon.edconnect.domain.repository.AuthRepository
import com.decagon.edconnect.utils.ApiResponseState
import com.decagon.edconnect.utils.Constants.AUTH_API_SERVICE
import com.decagon.edconnect.utils.GenericResponseClass
import com.decagon.edconnect.utils.Resource
import com.decagon.edconnect.utils.singleSourceManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Named

class AuthRepositoryImpl @Inject constructor (
    private val database: EdConnectDatabase,
    private val authenticationApiService: AuthenticationApiService,
    private val cacheUserMapper: CacheUserMapper,
    private val apiUserMapper: ApiUserMapper
) : AuthRepository, ApiResponseState() {
    override suspend fun registerUser(user: DomainUser): Resource<GenericResponseClass<String>> =

        responseState {
            authenticationApiService.registerUser(apiUserMapper.mapFromDomainModel(user))
        }

    override suspend fun login(email: String, password: String): Flow<Resource<DomainUser>> =
        singleSourceManager(
            fetchFromLocal = {
                flow{
                    emit (
                        cacheUserMapper.mapToDomainModel(database.userDao().getUser())
                    )
                }
            }, shouldFetchFromRemote = {
                true
            },
            fetchFromRemote = {
                authenticationApiService.login(email, password)
            },
            saveToLocalDB = {
                database.userDao().deleteUser()
                database.userDao().insertUser(apiUserMapper.mapFromApiToCacheModel(it.payload.user))
            }
        )

    override suspend fun loginUserWithGoogle(): Flow<Resource<DomainUser>> =
        singleSourceManager(
            fetchFromLocal = {
                flow{
                    emit (
                        cacheUserMapper.mapToDomainModel(database.userDao().getUser())
                    )
                }
            }, shouldFetchFromRemote = {
                true
            },
            fetchFromRemote = {
                authenticationApiService.loginUserWithGoogle()
            },
            saveToLocalDB = {
                database.userDao().deleteUser()
                database.userDao().insertUser(apiUserMapper.mapFromApiToCacheModel(it.payload.user))
            }
        )

    override suspend fun loginUserWithFacebook(): Flow<Resource<DomainUser>> =
        singleSourceManager(
            fetchFromLocal = {
                flow{
                    emit (
                        cacheUserMapper.mapToDomainModel(database.userDao().getUser())
                    )
                }
            }, shouldFetchFromRemote = {
                true
            },
            fetchFromRemote = {
                authenticationApiService.loginUserWithFacebook()
            },
            saveToLocalDB = {
                database.userDao().deleteUser()
                database.userDao().insertUser(apiUserMapper.mapFromApiToCacheModel(it.payload.user))
            }
        )

    override suspend fun changePassword(
        oldPassword: String,
        newPassword: String,
        confirmNewPassword: String

    ): Resource<GenericResponseClass<String>> =
        responseState {
        authenticationApiService.changePassword(oldPassword, newPassword, confirmNewPassword)
    }

    override suspend fun resetPassword(
        token: String,
        password: String,
        confirmPassword: String
    ): Resource<GenericResponseClass<String>> =
        responseState {
            authenticationApiService.resetPassword(token, password, confirmPassword)
        }

    override suspend fun requestPasswordChange(email: String): Resource<GenericResponseClass<String>>  =
        responseState {
            authenticationApiService.requestPasswordChange(email)
        }
}