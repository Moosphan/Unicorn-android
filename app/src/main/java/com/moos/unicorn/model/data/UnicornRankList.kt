package com.moos.unicorn.model.data

import java.io.Serializable

/**
 * Created by moosphon on 2019.08
 * the rank data of unicorn from the world.
 */
data class UnicornRankList (
    val id: String,
    val meta: MetaResult,
    val entities: List<UnicornResult>): Serializable {

    data class MetaResult(
        val updated_at: String,
        val total_valuation_amount: Double,
        val total_private_equity: Double,
        val entity_count: Int)
}