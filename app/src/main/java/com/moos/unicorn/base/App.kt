package com.moos.unicorn.base

import android.app.Application
import android.content.Context
import com.moos.unicorn.loader.GlideLoaderStrategy
import com.moos.unicorn.loader.ImageLoader
import kotlin.properties.Delegates

class App : Application() {

    companion object {
        var CONTEXT: Context by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        CONTEXT = applicationContext
        // initialize image loader
        ImageLoader.INSTANCE.init(GlideLoaderStrategy())
    }
}