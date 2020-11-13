package com.craft.furniture.di.builder

import com.craft.furniture.ui.home.HomeActivity
import com.craft.furniture.ui.registration.RegistrationActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {


    @ContributesAndroidInjector
    abstract fun bindHomeActivity() :HomeActivity

    @ContributesAndroidInjector
    abstract fun bindRegistrationActivity() :RegistrationActivity
}