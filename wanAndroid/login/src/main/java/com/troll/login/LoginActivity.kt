package com.troll.loginimport android.view.LayoutInflaterimport android.widget.Toastimport androidx.activity.viewModelsimport com.alibaba.android.arouter.facade.annotation.Routeimport com.troll.login.databinding.ActivityLoginBindingimport troll.btc.extensions.*import troll.eth.base.viewbinding.BaseActivityimport wan.common.RoutePath/** * author : TangPeng * date : 3/23/22 19:16 * description : 登录Activity MVI 形式 */@Route(path = RoutePath.LOGIN_ACTIVITY)class LoginActivity : BaseActivity<ActivityLoginBinding>() {    private val vm: LoginViewModel by viewModels()    override fun getBinding(inflater: LayoutInflater): ActivityLoginBinding =        ActivityLoginBinding.inflate(inflater)    override fun flowData() {        with(vm.viewStates) {            this.observeState(this@LoginActivity, LoginViewState::passwordTip) {                bd.loginTvPasswordTips.showOrHide(it)            }        }        vm.viewEvent.observeEvent(this) {            when (it) {                is LoginViewEvent.ShowToast ->                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()                is LoginViewEvent.ShowLoading -> {}                is LoginViewEvent.DismissLoading -> {}                is LoginViewEvent.LoginSuccess -> {                    println("登录成功¬")                }            }        }    }    override fun flowView() {        bd.loginEtPassword.addTextChangeListener(afterTextChanged = {            vm.dispatch(LoginViewAction.RefreshPasswordTips(it.toString()))        })        bd.loginBtLogin.onClick {            vm.dispatch(LoginViewAction.Login(bd.loginEtUserName.text(), bd.loginEtPassword.text()))        }    }}