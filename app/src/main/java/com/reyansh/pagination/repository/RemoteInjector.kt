package com.reyansh.pagination.repository

import com.reyansh.pagination.service.ApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RemoteInjector {
    private const val API_KEY = "live_IxljwqGc6XqqO7oKXulWMXjYly0QtN3oTR6c11u6ZzpwHcWEfRNxQnlqTlINWovG"
    private const val API_ENDPOINT = "https://api.thecatapi.com"
    private const val HEADER_API_KEY = "x-api-key"

    fun injectApiService(retrofit: Retrofit = getRetrofit()): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    private fun getRetrofit(okHttpClient: OkHttpClient = getOkHttpClient()): Retrofit {
        return Retrofit.Builder()
            .baseUrl(API_ENDPOINT)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    private fun getOkHttpNetworkInterceptor(): Interceptor {
        return Interceptor { chain ->
            val newRequest =
                chain.request().newBuilder().addHeader(HEADER_API_KEY, API_KEY).build()
            chain.proceed(newRequest)
        }
    }


    private fun getOkHttpClient(
        okHttpNetworkInterceptor: Interceptor = getOkHttpNetworkInterceptor()
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(okHttpNetworkInterceptor)
            .build()
    }

}