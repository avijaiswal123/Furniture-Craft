package com.craft.furniture.di.modules

import android.app.Application
import com.craft.furniture.data.local.db.DatabaseBuilder
import com.craft.furniture.di.scopes.AppScope
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
object AppDatabaseModule {
    @AppScope
    @Provides
    fun bindAppDatabase(context: Application) = DatabaseBuilder.getInstance(context)
}