package com.craft.furniture.di.modules

import androidx.lifecycle.ViewModel
import com.craft.furniture.ui.home.HomeViewModel
import com.craft.furniture.ui.registration.RegistrationViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass


@Module
interface ViewModelModule{
    @Binds
    @IntoMap
    @ViewModelKey(RegistrationViewModel::class)
    fun bindRegistrationViewModel(rvm :RegistrationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindHomeViewModel(hvm : HomeViewModel): ViewModel
}

@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)