package com.hariagus.mvp.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hariagus.mvp.R
import com.hariagus.mvp.adapter.PhotosAdapter
import com.hariagus.mvp.di.component.DaggerActivityComponent
import com.hariagus.mvp.di.module.ActivityModule
import com.hariagus.mvp.models.PhotosResponse
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainContract.Presenter

    private val adapter = GroupAdapter<ViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * init data
         */
        injectDependency()
        presenter.attach(this)

        initView()
    }

    private fun initView() {
        btnRequest.setOnClickListener {
            adapter.clear()
            val number = edIdAlbum.text.toString().toInt()
            presenter.getPhotos(number)
        }
    }

    override fun onSuccess(data: List<PhotosResponse>) {
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
        data.forEach { _ ->
            adapter.add(PhotosAdapter(data))
        }
        rvPhotoList.adapter = adapter
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