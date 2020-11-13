package com.craft.furniture.di.components

import android.app.Application
import com.craft.furniture.AppController
import com.craft.furniture.di.builder.ActivityBuilder

import com.craft.furniture.di.modules.*
import com.craft.furniture.di.scopes.AppScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@AppScope
@Component(modules = [
    AndroidInjectionModule::class,
    ApiHelperModule::class,
    DatabaseHelperModule:: class,
    RetrofitModule:: class,
    AppDatabaseModule:: class,
    ViewModelModule::class,
    ActivityBuilder::class
])

interface AppComponent :AndroidInjector<AppController>{

//    fun homeSubComponent(): HomeSubComponent
//    fun registerSubComponent(): RegisterSubComponent

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun appContext(application: Application): Builder
        fun build(): AppComponent
    }

    override fun inject(inject: AppController?)
}