package com.decagon.edconnect.di

import com.decagon.edconnect.data.datasources.localSource.EdConnectDatabase
import com.decagon.edconnect.data.datasources.localSource.model.CacheUser
import com.decagon.edconnect.data.datasources.localSource.model.mappers.CacheProjectMapper
import com.decagon.edconnect.data.datasources.localSource.model.mappers.CacheUserMapper
import com.decagon.edconnect.data.datasources.remoteSource.AuthenticationApiService
import com.decagon.edconnect.data.datasources.remoteSource.ProjectApiService
import com.decagon.edconnect.data.datasources.remoteSource.UserApiService
import com.decagon.edconnect.data.datasources.remoteSource.model.ApiUser
import com.decagon.edconnect.data.datasources.remoteSource.model.mappers.ApiProjectMapper
import com.decagon.edconnect.data.datasources.remoteSource.model.mappers.ApiUserMapper
import com.decagon.edconnect.data.repositoryImplementations.*
import com.decagon.edconnect.domain.model.DomainUser
import com.decagon.edconnect.domain.repository.AuthRepository
import com.decagon.edconnect.domain.repository.ProjectRepository
import com.decagon.edconnect.domain.repository.UserRepository
import com.decagon.edconnect.utils.DomainModelMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideUserRepository(
        database: EdConnectDatabase,
        userApiService: UserApiService,
        cacheUserMapper: CacheUserMapper,
        apiUserMapper: ApiUserMapper
    ): UserRepository { return UserRepositoryImpl(database, userApiService, cacheUserMapper, apiUserMapper) }

    @Singleton
    @Provides
    fun provideProjectRepository(
        database: EdConnectDatabase,
        projectApiService: ProjectApiService,
        cacheProjectMapper: CacheProjectMapper,
        apiProjectMapper: ApiProjectMapper
        ): ProjectRepository { return ProjectRepositoryImpl(database, projectApiService, cacheProjectMapper, apiProjectMapper ) }

    @Singleton
    @Provides
    fun provideAuthRepository(
        authenticationApiService: AuthenticationApiService,
        database: EdConnectDatabase,
        cacheUserMapper: CacheUserMapper,
        apiUserMapper: ApiUserMapper
    ): AuthRepository {
        return AuthRepositoryImpl(database, authenticationApiService,  cacheUserMapper, apiUserMapper)
    }
}