package com.hankkin.reading.utils

import android.R.attr.duration
import android.animation.Animator
import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Context
import android.support.design.widget.BottomSheetDialog
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.RotateAnimation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import android.widget.TextView
import com.afollestad.materialdialogs.MaterialDialog
import com.hankkin.library.utils.LogUtils
import com.hankkin.library.utils.ToastUtils
import com.hankkin.reading.R
import com.hankkin.reading.common.Constant
import com.hankkin.reading.ui.home.articledetail.CommonWebActivity


/**
 * Created by huanghaijie on 2018/7/10.
 */
object ViewHelper {

    /**
     * 设置下拉刷新组件
     */
    fun setRefreshLayout(context: Context?, isAutoRefresh: Boolean,
                         layout: SwipeRefreshLayout,
                         onRefreshListener: SwipeRefreshLayout.OnRefreshListener) {
        layout.setColorSchemeResources(ThemeHelper.getCurrentColor(context))
        layout.setOnRefreshListener(onRefreshListener)
        if (isAutoRefresh) layout.isRefreshing = true
    }

    fun changeRefreshColor(layout: SwipeRefreshLayout, context: Context?) {
        layout.setColorSchemeResources(ThemeHelper.getCurrentColor(context))
    }

    /**
     * normal dialog
     */
    fun showConfirmDialog(context: Context, content: String, callback: MaterialDialog.SingleButtonCallback) {
        MaterialDialog.Builder(context)
                .content(content)
                .positiveText(context.resources.getString(R.string.ok))
                .negativeText(context.resources.getString(R.string.cancel))
                .onPositive(callback)
                .show()

    }

    fun showProgressDIalog(context: Context,content: String){
        MaterialDialog.Builder(context)
                .content(content)
                .progress(true,0)
                .progressIndeterminateStyle(true)
                .widgetColor(context.resources.getColor(ThemeHelper.getCurrentColor(context)))
                .show()
    }

    /**
     * list dialog
     */
    fun showListNoTitleDialog(context: Context, list: MutableList<String>, calback: MaterialDialog.ListCallback) {
        MaterialDialog.Builder(context)
                .items(list)
                .itemsCallback(calback)
                .show()
    }


    /**
     * list dialog
     */
    fun showListTitleDialog(context: Context, title: String,list: MutableList<String>, calback: MaterialDialog.ListCallback) {
        MaterialDialog.Builder(context)
                .title(title)
                .titleColor(context.resources.getColor(ThemeHelper.getCurrentColor(context)))
                .items(list)
                .itemsCallback(calback)
                .show()
    }


    /**
     * about dialog
     */
    fun showAboutDialog(context: Context) {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_about_dialog, null)
        view.findViewById<ImageView>(R.id.iv_about_github).setOnClickListener { CommonWebActivity.loadUrl(context, Constant.AboutUrl.GITHUB, Constant.AboutUrl.GITHUB_TITLE) }
        view.findViewById<ImageView>(R.id.iv_about_juejin).setOnClickListener { CommonWebActivity.loadUrl(context, Constant.AboutUrl.JUEJIN, Constant.AboutUrl.JUEJIN_TITLE) }
        view.findViewById<ImageView>(R.id.iv_about_jianshu).setOnClickListener { CommonWebActivity.loadUrl(context, Constant.AboutUrl.JIANSHU, Constant.AboutUrl.JIANSHU_TITLE) }
        view.findViewById<ImageView>(R.id.iv_about_csdn).setOnClickListener { CommonWebActivity.loadUrl(context, Constant.AboutUrl.CSDN, Constant.AboutUrl.CSDN_TITLE) }
        val bottomSheet = BottomSheetDialog(context, R.style.BottomSheetDialog)
        view.findViewById<TextView>(R.id.tv_about_close).setOnClickListener { bottomSheet.dismiss() }
        view.findViewById<TextView>(R.id.tv_about_rate).setOnClickListener { ToastUtils.showInfo(context, "敬请期待") }
        bottomSheet.setContentView(view)
        bottomSheet.show()
    }

    /**
     * 抖动动画
     */
    fun startShakeAnim(view: View) {
        val scale = ScaleAnimation(0.95f, 1.05f, 0.95f, 1.05f)
        val rotate = RotateAnimation(-0.5f, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        scale.duration = 800
        rotate.duration = 80
        rotate.repeatMode = Animation.REVERSE
        rotate.repeatCount = 5
        val animSet = AnimationSet(false)
        animSet.addAnimation(scale)
        animSet.addAnimation(rotate)
        view.startAnimation(animSet)
        animSet.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                animSet.cancel()
            }

            override fun onAnimationStart(animation: Animation?) {
            }

        })
    }

}