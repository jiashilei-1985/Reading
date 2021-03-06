package com.hankkin.reading.ui.user.collect

import com.hankkin.reading.http.HttpClient
import com.hankkin.reading.mvp.presenter.RxLifePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MyCollectPresenter : RxLifePresenter<MyCollectContract.IView>(), MyCollectContract.IPresenter {

    override fun getCollectHttp(page: Int) {
        HttpClient.Builder.getCommonHttp()
                .getMyCollect(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeEx({
                    getMvpView().getCollectData(it)
                }, {
                    getMvpView().setFail()
                })
    }

    override fun collectHttp(id: Int) {
        HttpClient.Builder.getCommonHttp()
                .collectArticle(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeNx ({
                    getMvpView().collectResult(id)
                },{
                    getMvpView().setFail()
                }).bindRxLifeEx(RxLife.ON_DESTROY)
    }

    override fun cancelCollectHttp(id: Int) {
        HttpClient.Builder.getCommonHttp()
                .cancelCollectArticle(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeNx ({
                    getMvpView().cancelCollectResult(id)
                },{
                    getMvpView().setFail()
                }).bindRxLifeEx(RxLife.ON_DESTROY)
    }
}