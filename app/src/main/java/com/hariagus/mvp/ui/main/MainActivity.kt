package com.hariagus.mvp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hariagus.mvp.R
import com.hariagus.mvp.di.component.DaggerActivityComponent
import com.hariagus.mvp.di.module.ActivityModule
import dagger.android.DaggerActivity
import kotlinx.android.synthetic.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * init data
         */
        injectDependency()
        presenter.attach(this)


    }


    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .build()

        activityComponent.inject(this)
    }
}