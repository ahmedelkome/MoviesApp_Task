package com.route.data.utils

suspend fun <T>safeData(call: suspend () -> T): T {
    return try {
        val response = call.invoke()
        return response
    } catch (e: Throwable) {
        throw e
    }
}