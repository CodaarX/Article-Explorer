package com.decagon.edconnect.data.datasources.remoteSource.model.mappers

import com.decagon.edconnect.data.datasources.localSource.model.CacheProject
import com.decagon.edconnect.data.datasources.remoteSource.model.ApiProject
import com.decagon.edconnect.domain.model.DomainProject
import com.decagon.edconnect.utils.DomainModelMapper
import javax.inject.Inject

class ApiProjectMapper @Inject constructor(): DomainModelMapper<ApiProject, DomainProject> {
    override fun mapToDomainModel(model: ApiProject): DomainProject {
        return DomainProject(
            name = model.name,
            abstract = model.abstract,
            authors = model.authors,
            tags = model.tags
        )
    }

    override fun mapFromDomainModel(model: DomainProject): ApiProject {
       return ApiProject(
           name = model.name,
           abstract = model.abstract,
           authors = model.authors,
           tags = model.tags
       )
    }

    fun <T>  mapFromApiToCacheModel(model: ApiProject): T {
        return CacheProject(
            name = model.name,
            abstract = model.abstract,
            authors = model.authors,
            tags = model.tags
        ) as T
    }

    fun toDomainListMapper(cacheList: List<ApiProject>): List<DomainProject> {
        return cacheList.map { mapToDomainModel(it) }
    }

    fun formDomainListMapper(domainList: List<DomainProject>): List<ApiProject> {
        return domainList.map { mapFromDomainModel(it) }
    }

}