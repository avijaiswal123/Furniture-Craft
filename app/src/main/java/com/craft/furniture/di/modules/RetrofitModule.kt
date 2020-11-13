package com.craft.furniture.di.modules

import com.craft.furniture.data.remote.NetworkInterceptor
import com.craft.furniture.data.remote.RetrofitInstance
import com.craft.furniture.di.scopes.AppScope
import dagger.Module
import dagger.Provides
import javax.inject.Singleton



@Module
object RetrofitModule {
    @AppScope
    @Provides
    fun provideRetrofit(networkInterceptor: NetworkInterceptor)=
        RetrofitInstance.createRetrofit(networkInterceptor)
}