package com.moos.unicorn.model.data

/**
 * Created by © moosphon on 2019/08/15
 * unicorn funding career of world famous companies.
 */
data class FundingResult (
    val uuid: String,
    val investors: List<String>?,
    val lead_investor: List<String>?,
    val short_name: String,
    val valuation: String,
    val date: String
)