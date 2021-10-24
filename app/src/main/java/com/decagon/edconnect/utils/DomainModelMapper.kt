package com.decagon.edconnect.utils

import com.decagon.edconnect.data.datasources.localSource.model.CacheProject
import com.decagon.edconnect.data.datasources.remoteSource.model.ApiProject

interface DomainModelMapper<ExternalModel, DomainModel> {

    // takes an external model and returns domain model
    fun mapToDomainModel(model: ExternalModel): DomainModel

    // takes in a domain model and returns an external model
    fun mapFromDomainModel(model: DomainModel): ExternalModel

    }