package com.hariagus.mvp.ui.main

import android.util.Log
import com.hariagus.mvp.api.ApiServiceInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlin.math.log

class MainPresenter : MainContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private lateinit var view: MainContract.View
    private val apiService: ApiServiceInterface = ApiServiceInterface.create()

    override fun getPhotos(id: Int) {
        val subscribe = apiService.getPhotos(id).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                Log.d("TAG", "getPhotos: $result")
                view.onSuccess(result)
            }, { error ->
                val message = error.localizedMessage
                view.onError(message)
            })
        subscriptions.add(subscribe)
    }

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: MainContract.View) {
        this.view
    }
}