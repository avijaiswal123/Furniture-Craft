package com.craft.furniture.di.modules

import com.craft.furniture.data.remote.ApiHelper
import com.craft.furniture.data.remote.ApiHelperImpl
import com.craft.furniture.di.scopes.AppScope
import dagger.Binds
import dagger.Module


@Module
interface ApiHelperModule {
    @AppScope
    @Binds
    fun bindApiHelper(apiHelperImpl: ApiHelperImpl): ApiHelper
}