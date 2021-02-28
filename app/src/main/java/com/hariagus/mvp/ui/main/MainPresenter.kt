package com.hariagus.mvp.ui.main

import com.hariagus.mvp.api.ApiServiceInterface
import io.reactivex.disposables.CompositeDisposable

class MainPresenter : MainContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private lateinit var view: MainContract.View
    private val apiService: ApiServiceInterface = ApiServiceInterface.create()

    override fun subscribe() {
        TODO("Not yet implemented")
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: MainContract.View) {
        this.view
    }
}