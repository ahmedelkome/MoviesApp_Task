package com.route.domain.utils

import com.route.domain.common.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

suspend fun <T>toflow(getData: suspend () -> T): Flow<ResultWrapper<T>> {
    return flow {
        emit(ResultWrapper.Loading)
        val response = getData.invoke()
        emit(ResultWrapper.Success(response))
    }.flowOn(Dispatchers.IO)
        .catch { it ->
            emit(ResultWrapper.Failure(it))
        }
}