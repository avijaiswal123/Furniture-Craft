package com.craft.furniture.di.modules

import com.craft.furniture.data.local.db.DatabaseHelper
import com.craft.furniture.data.local.db.DatabaseHelperImpl
import com.craft.furniture.di.scopes.AppScope
import dagger.Binds
import dagger.Module


@Module
interface DatabaseHelperModule {
    @AppScope
    @Binds
    fun bindDatabaseHelper(databaseHelperImpl: DatabaseHelperImpl) :DatabaseHelper
}