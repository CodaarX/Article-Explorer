package com.decagon.edconnect.data.datasources.localSource.model.mappers

import com.decagon.edconnect.data.datasources.localSource.model.CacheProject
import com.decagon.edconnect.domain.model.DomainProject
import com.decagon.edconnect.utils.DomainModelMapper
import javax.inject.Inject

class CacheProjectMapper @Inject constructor() : DomainModelMapper<CacheProject, DomainProject> {
    override fun mapToDomainModel(model: CacheProject): DomainProject {
       return DomainProject(
           name = model.name,
           abstract = model.abstract,
           authors = model.authors,
           tags = model.tags

       )
    }

    override fun mapFromDomainModel(model: DomainProject): CacheProject {
        return CacheProject(
            name = model.name,
            abstract = model.abstract,
            authors = model.authors,
            tags = model.tags
        )
    }

    fun <T> toDomainListMapper(cacheList: List<CacheProject>): T {
        return cacheList.map {
            mapToDomainModel(it)
        } as T
    }

    fun formDomainListMapper(domainList: List<DomainProject>): List<CacheProject> {
        return domainList.map { mapFromDomainModel(it) }
    }


}