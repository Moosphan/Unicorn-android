package com.moos.unicorn

import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope

/**
 * Check current network state is available or not.
 */
fun Context.isNetworkAvailable(): Boolean {
    val manager = this.applicationContext.getSystemService(
        Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val info = manager.activeNetworkInfo
    return !(null == info || !info.isAvailable)
}

/**
 * dispatch network response
 */
suspend fun <T: Any> dispatchResponse(response: T?, success: suspend CoroutineScope.()->Unit,
                                      error: suspend CoroutineScope.()->Unit) {
    coroutineScope {
        if (response == null) error else success
    }
}

/**
 * apply visibility setting to views.
 */
fun View.applyViewGone(gone: Boolean) {
    this.visibility = if (gone) View.GONE else View.VISIBLE
}