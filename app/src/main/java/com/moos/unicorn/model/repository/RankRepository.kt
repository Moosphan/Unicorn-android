package com.moos.unicorn.model.repository

import com.moos.unicorn.model.api.RetrofitClient
import com.moos.unicorn.model.data.UnicornRankList
import com.moos.unicorn.model.data.UnicornResult

/**
 * the unicorn rank data repository.
 */
class RankRepository {
    suspend fun requestRankData(): UnicornRankList {
        return RetrofitClient.INSTANCE.getService().getUnicornRankList()
    }

    fun getRankList(data: UnicornRankList): ArrayList<UnicornResult> {
        return data.entities as ArrayList<UnicornResult>
    }
}