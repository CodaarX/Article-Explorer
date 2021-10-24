package com.decagon.edconnect.utils

import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import retrofit2.Response

//    is SQLiteException -> {
//        Resource.Error(false, message = "Something went wrong", errorBody = null)
//    }

inline fun <DB, API> singleSourceManager(
    crossinline fetchFromLocal: suspend () -> Flow<DB>,
    crossinline fetchFromRemote: suspend () -> API,
    crossinline saveToLocalDB: suspend (API) -> Unit,
    crossinline shouldFetchFromRemote: () -> Boolean
) = flow {

    val localData = fetchFromLocal().first()

    val dataStream = if (shouldFetchFromRemote()) {
        emit(Resource.Loading(localData, "Loading"))
        try {
            saveToLocalDB(fetchFromRemote())
            fetchFromLocal().map { dbData -> Resource.Success(dbData) }
        } catch (throwable: Throwable) {
            when (throwable) {
                is HttpException -> {
                    fetchFromLocal().map {
                        Resource.Error(false, throwable.response() as Response<Any>, it)
                    }
                }
                else -> {
                    fetchFromLocal().map { Resource.Error(true, null, it) }
                }
            }
        }
    } else {
        fetchFromLocal().map { Resource.Success(it) }
    }
    emitAll(dataStream)
}
