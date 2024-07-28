package com.route.data.utils

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val token: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()
            .header("Authorization", "Bearer $token")
            .method(originalRequest.method, originalRequest.body)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}