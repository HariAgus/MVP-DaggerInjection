package com.hariagus.mvp.ui.main

import com.hariagus.mvp.models.PhotosResponse
import com.hariagus.mvp.base.BaseContract

class MainContract {

    interface View: BaseContract.View {
        fun onSuccess(data: List<PhotosResponse>)
        fun onError(message: String)
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun getPhotos(id: Int)
    }

}