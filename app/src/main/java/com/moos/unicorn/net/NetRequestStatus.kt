package com.moos.unicorn.net

/**
 * by moosphon on 2019/08/15
 * desc: status of network request
 */
object NetRequestStatus {
    const val SUCCESS = 1000

    /** 网络故障*/
    const val NETWORK_ERROR = 1001

    /** 服务器问题*/
    const val SERVER_ERROR = 1002

    /** API解析失败（或data结构发生变更等）*/
    const val PARSE_ERROR = 1003

    /** 未知错误*/
    const val UNKNOWN_ERROR = 1004

}