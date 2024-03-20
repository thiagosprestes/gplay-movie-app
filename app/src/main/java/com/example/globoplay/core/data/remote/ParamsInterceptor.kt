package com.example.globoplay.core.data.remote

import okhttp3.Interceptor
import okhttp3.Response

class ParamsInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.newBuilder()
            .addQueryParameter("lang", "pt_br")
            .addQueryParameter("api_key", "")
            .build()

        val newRequest = request.newBuilder().url(url).build()

        return chain.proceed(newRequest)
    }
}