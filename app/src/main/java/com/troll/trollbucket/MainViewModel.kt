package com.troll.trollbucketimport troll.btc.extensions.Liveimport troll.eth.base.viewbinding.BaseViewModelimport troll.kotlin.coroutines.CoroutinesActivityimport troll.kotlin.flow.FlowActivityimport troll.kotlin.shortCut.ShortCutActivityimport wan.main.WanActivityclass MainViewModel : BaseViewModel() {    val live = Live<MutableList<Main>>()    val liveActivity = Live<Class<*>>()    fun data() {        live.value = mutableListOf<Main>().apply {            add(Main("协程", MainTye.COROUTINES))//            add(Main("Retrofit", MainTye.RETROFIT))            add(Main("Bus", MainTye.BUS))            add(Main("玩Android", MainTye.WAN_ANDROID))//            add(Main("Compose", MainTye.COMPOSE))            add(Main("快捷方式", MainTye.SHORT_CUT))            add(Main("Flow", MainTye.FLOW))        }    }    fun processActivity(type: MainTye) {        when (type) {            MainTye.COROUTINES -> liveActivity.value = CoroutinesActivity::class.java            MainTye.FLOW -> liveActivity.value = FlowActivity::class.java            MainTye.WAN_ANDROID -> liveActivity.value = WanActivity::class.java            MainTye.SHORT_CUT -> liveActivity.value = ShortCutActivity::class.java        }    }}