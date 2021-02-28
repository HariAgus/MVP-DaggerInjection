package com.hariagus.mvp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.hariagus.mvp.R
import com.hariagus.mvp.di.component.DaggerActivityComponent
import com.hariagus.mvp.di.module.ActivityModule
import com.hariagus.mvp.models.PhotosResponse
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

    override fun onSuccess(data: List<PhotosResponse>) {
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
    }

    override fun onError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .build()

        activityComponent.inject(this)
    }
}