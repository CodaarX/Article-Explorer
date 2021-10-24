package com.decagon.edconnect.data.datasources.localSource.model.mappers

import com.decagon.edconnect.data.datasources.localSource.model.CacheUser
import com.decagon.edconnect.domain.model.DomainUser
import com.decagon.edconnect.utils.DomainModelMapper
import okhttp3.Cache
import javax.inject.Inject

class CacheUserMapper @Inject constructor(): DomainModelMapper<CacheUser, DomainUser> {

    override fun mapToDomainModel(model: CacheUser): DomainUser {
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

    override fun mapFromDomainModel(model: DomainUser): CacheUser {
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