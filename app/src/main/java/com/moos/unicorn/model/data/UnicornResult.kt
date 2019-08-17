package com.moos.unicorn.model.data

import java.io.Serializable

/**
 * Created by © moosphon on 2019/08/15
 * unicorn info of world famous companies.
 */
data class UnicornResult (
    val name: String,
    val logo_url: String,
    val permalink: String,
    val tag_page: String,
    val country: String,
    val last_funding_on: String,
    val total_equity_funding: Double,
    val founded_on: Long,
    val unicorn_bday: Long?,
    val unicorn_exit: Long?,
    val category: String,
    val rumored: Int,
    val post_money_val: Double,
    val valuation_change: Int
    //val funding_rounds: List<FundingResult>? = null
) : Serializable