package com.moos.unicorn.net

import android.util.Log
import com.google.gson.JsonParseException
import org.json.JSONException
import java.net.ConnectException
import java.net.SocketException
import java.net.UnknownHostException
import java.text.ParseException

/**
 * @author moosphon on 2019/08/15
 * desc: exception deal
 */
class ExceptionHandler {

    companion object {
        @JvmField
        var errorCode = NetRequestStatus.UNKNOWN_ERROR

        @JvmField
        var errorMessage = "请求失败，请稍后重试"

        @JvmStatic
        fun handleException(e : Throwable): String{
            e.printStackTrace()
            when(e){

                is SocketException  -> {
                    Log.e("ExceptionHandler", "网络连接异常： " + e.message)
                    errorCode = NetRequestStatus.NETWORK_ERROR
                    errorMessage = "网络连接异常"
                }

                is ConnectException -> {
                    Log.e("ExceptionHandler", "网络连接异常： " + e.message)
                    errorCode = NetRequestStatus.NETWORK_ERROR
                    errorMessage = "服务器连接异常，请稍后重试"
                }

                is JsonParseException -> {
                    Log.e("ExceptionHandler", "数据解析异常： " + e.message)
                    errorCode = NetRequestStatus.PARSE_ERROR
                    errorMessage = "数据解析异常"
                }

                is JSONException -> {
                    Log.e("ExceptionHandler", "数据解析异常： " + e.message)
                    errorCode = NetRequestStatus.SERVER_ERROR
                    errorMessage = "数据解析异常"
                }

                is ParseException -> {
                    Log.e("ExceptionHandler", "数据解析异常： " + e.message)
                    errorCode = NetRequestStatus.SERVER_ERROR
                    errorMessage = "数据解析异常"
                }

                is ApiServiceException -> {
                    //服务器返回的错误信息
                    Log.e("ExceptionHandler", "服务器的异常： " + e.message)
                    errorCode = NetRequestStatus.SERVER_ERROR
                    errorMessage = e.message.toString()
                }

                is UnknownHostException -> {
                    Log.e("ExceptionHandler", "网络连接异常： " + e.message)
                    errorCode = NetRequestStatus.NETWORK_ERROR
                    errorMessage = "网络连接异常"
                }

                is IllegalArgumentException -> {
                    Log.e("ExceptionHandler", "参数出现错误： " + e.message)
                    errorCode = NetRequestStatus.NETWORK_ERROR
                    errorMessage = "参数错误"
                }

                else -> {
                    try {
                        Log.e("ExceptionHandler", "其他错误： " + e.message)
                    } catch (e1: Exception) {
                        Log.e("ExceptionHandler", "未知错误： " + e.message)
                    }

                    errorCode = NetRequestStatus.UNKNOWN_ERROR
                    errorMessage = "未知错误，一起祷告快点好起来吧～"
                }
            }

            return errorMessage
        }

    }


}