package com.decagon.edconnect.di

import com.decagon.edconnect.data.datasources.localSource.model.CacheProject
import com.decagon.edconnect.data.datasources.localSource.model.CacheUser
import com.decagon.edconnect.data.datasources.localSource.model.mappers.CacheProjectMapper
import com.decagon.edconnect.data.datasources.localSource.model.mappers.CacheUserMapper
import com.decagon.edconnect.data.datasources.remoteSource.model.ApiProject
import com.decagon.edconnect.data.datasources.remoteSource.model.ApiUser
import com.decagon.edconnect.data.datasources.remoteSource.model.mappers.ApiProjectMapper
import com.decagon.edconnect.data.datasources.remoteSource.model.mappers.ApiUserMapper
import com.decagon.edconnect.domain.model.DomainProject
import com.decagon.edconnect.domain.model.DomainUser
import com.decagon.edconnect.utils.DomainModelMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapperProviders {

    @Singleton
    @Provides
    fun provideCacheProjectMapper() : DomainModelMapper<CacheProject, DomainProject> {
        return CacheProjectMapper()
    }

    @Singleton
    @Provides
    fun provideApiProjectMapper() : DomainModelMapper<ApiProject, DomainProject> {
        return ApiProjectMapper()
    }

    @Singleton
    @Provides
    fun provideCacheUserMapper() : DomainModelMapper<CacheUser, DomainUser> {
        return CacheUserMapper()
    }

    @Singleton
    @Provides
    fun provideApiUserMapper() : DomainModelMapper<ApiUser, DomainUser> {
        return ApiUserMapper()
    }

}