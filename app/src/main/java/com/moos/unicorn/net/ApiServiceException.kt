package com.moos.unicorn.net

/**
 * by moosphon on 2019/08/15
 * desc: 接口调用时异常的统一封装和处理
 */
class ApiServiceException : RuntimeException{
    private var code: Int? = null

    constructor(throwable: Throwable, code: Int): super(throwable){
        this.code = code
    }

    constructor(message: String): super(Throwable(message))
}