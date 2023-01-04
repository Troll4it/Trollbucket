package com.troll.commonimport android.app.Activityimport androidx.multidex.MultiDexApplicationimport com.alibaba.android.arouter.launcher.ARouterimport com.blankj.utilcode.util.Utilsimport troll.btc.log.TLogimport troll.eth.ui.web.WebViewPoolimport troll.fitter.AdaptListenerimport troll.fitter.FitterConfigimport troll.fitter.FitterSize/** * author : TangPeng * date : 1/4/23 15:49 * description : */open class CommonApplication : MultiDexApplication() {    override fun onCreate() {        super.onCreate()        FitterSize.initCompatMultiProcess(this)        Utils.init(this)        ARouter.openLog()        ARouter.openDebug()        ARouter.init(this)        FitterConfig.get().addAdaptListener(object : AdaptListener {            override fun onAdaptBefore(target: Any, activity: Activity) {                TLog.i("适配前${activity.javaClass.name}")            }            override fun onAdaptAfter(target: Any, activity: Activity) {                TLog.i("适配后${activity.javaClass.name}")            }        })        WebViewPool.getInstance().setMaxPoolSize(            Integer.min(                Runtime.getRuntime().availableProcessors(), 3            )        )        WebViewPool.getInstance().init(applicationContext)    }}