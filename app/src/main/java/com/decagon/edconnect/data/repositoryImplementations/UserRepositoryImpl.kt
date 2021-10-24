package com.decagon.edconnect.data.repositoryImplementations

import com.decagon.edconnect.data.datasources.localSource.EdConnectDatabase
import com.decagon.edconnect.data.datasources.localSource.model.mappers.CacheUserMapper
import com.decagon.edconnect.data.datasources.remoteSource.UserApiService
import com.decagon.edconnect.data.datasources.remoteSource.model.mappers.ApiUserMapper
import com.decagon.edconnect.domain.model.DomainUser
import com.decagon.edconnect.domain.repository.UserRepository
import com.decagon.edconnect.utils.Constants.USER_API_SERVICE
import com.decagon.edconnect.utils.Resource
import com.decagon.edconnect.utils.singleSourceManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Named

class UserRepositoryImpl @Inject constructor (
    private val database: EdConnectDatabase,
    private val userApiService: UserApiService,
    private val cacheUserMapper: CacheUserMapper,
    private val apiUserMapper: ApiUserMapper
) : UserRepository {

    override suspend fun getProfile(id: String): Flow<Resource<DomainUser>> =
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
                userApiService.getProfile(id)
            },
            saveToLocalDB = {
                database.userDao().deleteUser()
                database.userDao().insertUser(apiUserMapper.mapFromApiToCacheModel(it.payload))
            }
        )

    override suspend fun updateProfile(
        id: String,
        user: DomainUser
    ): Flow<Resource<DomainUser>>  =

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
                userApiService.updateProfile(id, apiUserMapper.mapFromDomainModel(user))
            },
            saveToLocalDB = {
                database.userDao().deleteUser()
                database.userDao().insertUser(apiUserMapper.mapFromApiToCacheModel(it.payload))
            }
        )

    override suspend fun logout() = userApiService.logout()

}