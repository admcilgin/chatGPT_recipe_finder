package com.acilgin.chatgpt_recipe_finder.common.utils

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthInterceptor(private val apiKey: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest: Request =
            chain.request()
                .newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer $apiKey").build()

        return chain.proceed(newRequest)
    }

}