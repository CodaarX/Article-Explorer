package com.decagon.edconnect.utils

import android.database.sqlite.SQLiteException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response

abstract class ApiResponseState {
    suspend fun <T> responseState(apiCall: suspend () -> T): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        Resource.Error(false, throwable.response() as Response<Any>)
                    }
                    else -> {
                        Resource.Error(true, null, null)
                    }
                }
            }
        }
    }

}