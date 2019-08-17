package com.moos.unicorn.model.repository

import com.moos.unicorn.model.api.RetrofitClient
import com.moos.unicorn.model.data.UnicornResult

/**
 * find the unicorn by name data repository.
 */
class SearchRepository {
    suspend fun findUnicorn(unicornName: String): UnicornResult {
        return RetrofitClient.INSTANCE.getService().findUnicorn(unicornName)
    }
}