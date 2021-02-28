package com.hariagus.mvp.di.component

import com.hariagus.mvp.di.module.ActivityModule
import com.hariagus.mvp.ui.main.MainActivity
import dagger.Component

@Component(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)

}