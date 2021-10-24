package com.decagon.edconnect.data.datasources.remoteSource.model.mappers

import com.decagon.edconnect.data.datasources.localSource.model.CacheUser
import com.decagon.edconnect.data.datasources.remoteSource.model.ApiProject
import com.decagon.edconnect.data.datasources.remoteSource.model.ApiUser
import com.decagon.edconnect.domain.model.DomainUser
import com.decagon.edconnect.utils.DomainModelMapper
import javax.inject.Inject

class ApiUserMapper @Inject constructor() : DomainModelMapper<ApiUser, DomainUser> {
    override fun mapToDomainModel(model: ApiUser): DomainUser {
        return DomainUser(
            firstName = model.firstName,
            lastName = model.lastName,
            password = model.password,
            email = model.email,
            matricNumber = model.matricNumber,
            program = model.program,
            graduationYear = model.graduationYear
        )
    }

    override fun mapFromDomainModel(model: DomainUser): ApiUser {
        return ApiUser(
            firstName = model.firstName,
            lastName = model.lastName,
            password = model.password,
            email = model.email,
            matricNumber = model.matricNumber,
            program = model.program,
            graduationYear = model.graduationYear
        )
    }

    fun mapFromApiToCacheModel(model: ApiUser): CacheUser {
        return CacheUser(
            firstName = model.firstName,
            lastName = model.lastName,
            password = model.password,
            email = model.email,
            matricNumber = model.matricNumber,
            program = model.program,
            graduationYear = model.graduationYear
        )
    }

}