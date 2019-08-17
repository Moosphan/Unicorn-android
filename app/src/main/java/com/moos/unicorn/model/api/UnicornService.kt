package com.moos.unicorn.model.api

import com.moos.unicorn.model.data.UnicornRankList
import com.moos.unicorn.model.data.UnicornResult
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by © moosphon on 2019/08/15
 * the app service for unicorn rank.
 */
interface UnicornService {

    /**
     * Get rank list of world's unicorns
     */
    @GET("/unicorns")
    suspend fun getUnicornRankList(): UnicornRankList

    /**
     * Search unicorn from rank by com-name.
     */
    @GET("/unicorns/{name}")
    suspend fun findUnicorn(@Path("name") name: String): UnicornResult
}