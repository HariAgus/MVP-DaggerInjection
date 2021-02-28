package com.hariagus.mvp.di.module

import android.app.Activity
import com.hariagus.mvp.ui.main.MainContract
import com.hariagus.mvp.ui.main.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private var activity: Activity) {

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun providePresenter(): MainContract.Presenter {
        return MainPresenter()
    }

}