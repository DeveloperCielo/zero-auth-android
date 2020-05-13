package br.com.cielo.zeroauth.internal.network

import br.com.cielo.zeroauth.BuildConfig
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

internal class WebClient(apiUrl: String) {
    private val retrofit = Retrofit.Builder()
        .baseUrl(apiUrl)
        .addConverterFactory(GsonConverterFactory.create())

    private val httpClient = OkHttpClient().newBuilder()

    fun <T> createService(service: Class<T>): T {
        val logging = HttpLoggingInterceptor()
        logging.level =
            if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE

        val client = httpClient
            .addInterceptor(logging)
            .connectTimeout(45, TimeUnit.SECONDS)
            .readTimeout(45, TimeUnit.SECONDS)
            .writeTimeout(45, TimeUnit.SECONDS)
            .build()

        return retrofit
            .client(client)
            .build()
            .create(service)
    }
}