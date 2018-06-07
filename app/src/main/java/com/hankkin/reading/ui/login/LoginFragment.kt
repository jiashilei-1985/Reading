package com.hankkin.reading.ui.login

import android.widget.ImageView
import butterknife.BindView
import butterknife.OnClick
import com.hankkin.reading.R
import com.hankkin.reading.base.BaseFragment
import com.hankkin.reading.domain.BaseResponse
import com.hankkin.reading.domain.CaptchaBean
import com.hankkin.reading.domain.CsrfTokenBean
import com.hankkin.reading.domain.UserBean
import com.hankkin.reading.utils.LoadingUtils
import com.hankkin.reading.utils.ToastUtils
import com.wuba.guchejia.img.ImageLoader
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*

/**
 * Created by huanghaijie on 2018/5/15.
 */
class LoginFragment : BaseFragment<LoginContract.IPresenter>(), LoginContract.IView {

    private lateinit var code:String

    override fun loginResult(userBean: UserBean) {
        ToastUtils.showToast(context,"登录成功"+userBean.username)
    }

    @BindView(R.id.iv_login_code) lateinit var ivCode: ImageView

    override fun createmPresenter() = LoginPresenter(this)


    override fun getLayoutId(): Int {
        return R.layout.fragment_login
    }

    override fun initData() {
    }

    override fun initViews() {
        getmPresenter().getCapchaHttp()
    }

    @OnClick(R.id.iv_login_code) fun codeClick(){
        getmPresenter().getCapchaHttp()
    }

    @OnClick(R.id.tv_login_btn) fun loginClick(){
        val map = HashMap<String,Any>()
        map.put("username",et_login_name.text)
        map.put("password",et_login_pwd.text)
        map.put("captcha_1",et_login_code.text)
        map.put("captcha_0",code)
        getmPresenter().loginHttp(map)
    }

    override fun showLoading() {
        LoadingUtils.showLoading(context)
    }

    override fun hideLoading() {
        LoadingUtils.hideLoading()
    }

    override fun getCapcha(captchaBean: CaptchaBean) {
        code = captchaBean.key
        if (captchaBean.image_url.isNotEmpty()){
            ImageLoader.load(context,captchaBean.image_url,ivCode)
        }
    }


}