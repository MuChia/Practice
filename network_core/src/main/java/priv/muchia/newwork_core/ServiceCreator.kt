package priv.muchia.newwork_core

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * FileName: ServiceCreator
 * Author: MuChia
 * Date: 2022/6/8 00:24
 * Description:
 */
object ServiceCreator {
    private const val BASE_URL = "https://www.wanandroid.com"
    private val logger = HttpLoggingInterceptor().apply { level = Level.BASIC }
    private val client = OkHttpClient.Builder()
        .addInterceptor(logger)
        .build()
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)
    inline fun <reified T> create(): T = create(T::class.java)
}