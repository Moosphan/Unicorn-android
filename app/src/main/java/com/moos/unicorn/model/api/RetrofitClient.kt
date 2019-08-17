package com.moos.unicorn.model.api

import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.moos.unicorn.BuildConfig
import com.moos.unicorn.base.App
import com.moos.unicorn.isNetworkAvailable
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Generate retrofit okhttp client for network request.
 */
class RetrofitClient {
    companion object {
        private const val TIME_OUT = 5
        private const val BASE_URL = "http://49.234.230.70:3000"

        val INSTANCE by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { RetrofitClient() }

    }


    private val cookieJar by lazy { PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(App.CONTEXT)) }

    private val client: OkHttpClient
        get() {
            val builder = OkHttpClient.Builder()
            val logging = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                logging.level = HttpLoggingInterceptor.Level.BODY
            } else {
                logging.level = HttpLoggingInterceptor.Level.BASIC
            }

            builder.addInterceptor(logging)
                .connectTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)

            dealHttpCache(builder)

            return builder.build()
        }

    private fun dealHttpCache(builder: OkHttpClient.Builder) {
        val httpCacheDirectory = File(App.CONTEXT.cacheDir, "responses")
        val cacheSize = 10 * 1024 * 1024L // 10 MiB
        val cache = Cache(httpCacheDirectory, cacheSize)
        builder.cache(cache)
            .cookieJar(cookieJar)
            .addInterceptor { chain ->
                var request = chain.request()
                if (!App.CONTEXT.isNetworkAvailable()) {
                    request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build()
                }
                val response = chain.proceed(request)
                if (!App.CONTEXT.isNetworkAvailable()) {
                    val maxAge = 60 * 60
                    response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, max-age=$maxAge")
                        .build()
                } else {
                    val maxStale = 60 * 60 * 24 * 28 // tolerate 4-weeks stale
                    response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                        .build()
                }

                response
            }
    }

    fun getService() =
            Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build().create(UnicornService::class.java)
}