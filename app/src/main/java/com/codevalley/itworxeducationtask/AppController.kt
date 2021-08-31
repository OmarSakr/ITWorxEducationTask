package com.codevalley.itworxeducationtask

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Build
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.codevalley.itworxeducationtask.utils.ParentClass
import java.util.*


class AppController : MultiDexApplication() {

    var sharedPreferences_language: SharedPreferences? = null
    override fun onCreate() {
        super.onCreate()
        sharedPreferences_language = getSharedPreferences("language", MODE_PRIVATE)

    }


    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }


    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        setLocale()
    }



    //
    private fun setLocale() {
        val locale = Locale(ParentClass.getLang(this))
        Locale.setDefault(locale)
        val config = Configuration()

        config.locale = locale
        baseContext.resources.updateConfiguration(
            config,
            baseContext.resources.displayMetrics
        )
    }


}