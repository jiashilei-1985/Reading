package com.hankkin.reading.ui.home.hot.hotlist

import com.hankkin.reading.domain.ArticleBean
import com.hankkin.reading.domain.BannerBean
import com.hankkin.reading.domain.CateBean
import com.hankkin.reading.mvp.contract.IBaseViewContract
import com.hankkin.reading.mvp.contract.IPresenterContract

/**
 * Created by huanghaijie on 2018/7/8.
 */
interface HotListContact {
    interface IView : IBaseViewContract {
        fun setBanner(banner: MutableList<BannerBean>)
        fun setData(data: ArticleBean)
    }

    interface IPresenter : IPresenterContract {
        fun queryKey(page: Int,key: String)
        fun getBannerHttp()
    }
}