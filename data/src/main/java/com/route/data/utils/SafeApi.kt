package com.route.data.utils

suspend fun <T>safeApi(apiCall: suspend () -> T): T {
    return try {
        val response = apiCall.invoke()
        return response
    } catch (e: Throwable) {
        throw e
    }
}