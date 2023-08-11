package com.troll.trollbucketimport troll.btc.extensions.Liveimport troll.eth.base.viewbinding.BaseViewModelimport troll.kotlin.animation.AnimationDetailActivityimport troll.kotlin.button.ButtonActivityimport troll.kotlin.coroutines.CoroutinesActivityimport troll.kotlin.dialog.DialogActivityimport troll.kotlin.jetpack.JetpackActivityimport troll.kotlin.jetpack.LiveDataActivityimport troll.kotlin.kotlin.KotlinDemoActivityimport troll.kotlin.notification.NotificationActivityimport troll.kotlin.reflect.ReflectActivityimport troll.kotlin.service.ServiceActivityimport troll.kotlin.shortCut.ShortCutActivityimport troll.kotlin.softinput.SoftInputActivityimport troll.kotlin.timer.TimerActivityimport troll.kotlin.view.CoordinateSystemActivityimport troll.kotlin.view.CustomViewActivityimport troll.kotlin.widget.WidgetActivityimport wan.main.WanActivityclass MainViewModel : BaseViewModel() {    val live = Live<MutableList<Main>>()    val liveActivity = Live<Class<*>>()    fun data() {        live.value = mutableListOf<Main>().apply {            add(Main("协程", MainTye.COROUTINES))//            add(Main("Retrofit", MainTye.RETROFIT))            add(Main("Bus", MainTye.BUS))            add(Main("玩Android", MainTye.WAN_ANDROID))//            add(Main("Compose", MainTye.COMPOSE))            add(Main("快捷方式", MainTye.SHORT_CUT))            add(Main("Flow", MainTye.FLOW))            add(Main("软键盘", MainTye.SOFT_INPUT))            add(Main("反射", MainTye.REFLECT))            add(Main("LiveData", MainTye.LIVE_DATA))            add(Main("Fragment", MainTye.FRAMENT))            add(Main("Demo测试", MainTye.Test))            add(Main("通知栏", MainTye.NOTIFICATION))            add(Main("服务", MainTye.SERVICE))            add(Main("Kotlin小Demo", MainTye.kOTLIN_DEMO))            add(Main("按钮开关", MainTye.BUTTON))            add(Main("桌面组件", MainTye.WIDGET))            add(Main("倒计时", MainTye.TIMER))            add(Main("重叠View的坐标转换", MainTye.COORDINATE_SYSTEM))            add(Main("Jetpack", MainTye.JETPACK))            add(Main("自定义View", MainTye.CUSTOM_VIEW))            add(Main("动画", MainTye.ANIMATION))        }    }    fun processActivity(type: MainTye) {        when (type) {            MainTye.COROUTINES -> liveActivity.value = CoroutinesActivity::class.java            MainTye.WAN_ANDROID -> liveActivity.value = WanActivity::class.java            MainTye.SHORT_CUT -> liveActivity.value = ShortCutActivity::class.java            MainTye.SOFT_INPUT -> liveActivity.value = SoftInputActivity::class.java            MainTye.REFLECT -> liveActivity.value = ReflectActivity::class.java            MainTye.LIVE_DATA -> liveActivity.value = LiveDataActivity::class.java            MainTye.FRAMENT -> liveActivity.value = DialogActivity::class.java            MainTye.NOTIFICATION -> liveActivity.value = NotificationActivity::class.java            MainTye.SERVICE -> liveActivity.value = ServiceActivity::class.java            MainTye.kOTLIN_DEMO -> liveActivity.value = KotlinDemoActivity::class.java            MainTye.BUTTON -> liveActivity.value = ButtonActivity::class.java            MainTye.TIMER -> liveActivity.value = TimerActivity::class.java            MainTye.COORDINATE_SYSTEM -> liveActivity.value = CoordinateSystemActivity::class.java            MainTye.JETPACK -> liveActivity.value = JetpackActivity::class.java            MainTye.CUSTOM_VIEW -> liveActivity.value = CustomViewActivity::class.java            MainTye.WIDGET -> liveActivity.value = WidgetActivity::class.java            MainTye.ANIMATION -> liveActivity.value = AnimationDetailActivity::class.java        }    }}