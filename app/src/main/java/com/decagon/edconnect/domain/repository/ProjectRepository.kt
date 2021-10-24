package com.decagon.edconnect.domain.repository

import com.decagon.edconnect.data.datasources.localSource.model.CacheProject
import com.decagon.edconnect.data.datasources.remoteSource.model.ApiProject
import com.decagon.edconnect.domain.model.DomainProject
import com.decagon.edconnect.utils.GenericResponseClass
import com.decagon.edconnect.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ProjectRepository {
    // we are expecting a flow because we will be using the single source of truth
    suspend fun getAllProjects(fetchFromRemote: Boolean): Flow<Resource<List<DomainProject>>>
    suspend fun getSingleProject(id: String, fetchFromRemote: Boolean): Flow<Resource<DomainProject>>
    suspend fun createProject(project: DomainProject): Flow<Resource<List<DomainProject>>>
    suspend fun updateProject(id: String, project: DomainProject): Flow<Resource<DomainProject>>
    suspend fun deleteProject(id: String): Flow<Resource<List<DomainProject>>>
    suspend fun addFavouriteProject(id: String): Flow<Resource<List<DomainProject>>>
    suspend fun deleteFavouriteProject(id: String): Flow<Resource<List<DomainProject>>>
    suspend fun getFavouriteProjects(fetchFromRemote: Boolean): Flow<Resource<List<DomainProject>>>
    suspend fun searchProject(text: String, fetchFromRemote: Boolean) :  Flow<Resource<List<DomainProject>>>
    suspend fun projectsCreatedByMe(fetchFromRemote: Boolean): Flow<Resource<List<DomainProject>>>

}