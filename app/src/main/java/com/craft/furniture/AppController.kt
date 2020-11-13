package com.craft.furniture

import android.app.Application
import android.content.SharedPreferences
import com.craft.furniture.data.local.pref.SharedPref
import com.craft.furniture.di.components.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class AppController : Application() ,HasAndroidInjector{

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        initDagger()
        /**
         * Initialize [SharedPreferences]
         */
        SharedPref.initSharedPreferences(applicationContext)

    }
    /**
     * Initialize [DaggerAppComponent]
     */
    private fun initDagger() {
        DaggerAppComponent
            .builder()
            .appContext(
                applicationContext as Application
            ).build()
            .inject(this)
    }
    override fun androidInjector()= androidInjector
}