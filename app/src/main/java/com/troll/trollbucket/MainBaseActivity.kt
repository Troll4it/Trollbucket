package com.troll.trollbucket

import android.view.LayoutInflater
import com.troll.bus.channel.ChannelBus
import com.troll.trollbucket.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import troll.btc.extensions.onClick
import troll.btc.extensions.startAc
import troll.eth.base.viewbinding.BaseActivity
import troll.kotlin.channelbus.ChannelBusActivity
import troll.kotlin.coroutines.CoroutinesActivity
import troll.kotlin.retrofit.RetrofitActivity


/**
 * MVI:
 *
 * 1. 用户操作Intent的形式，来通知Model
 * 2. Model基于Intent的形式来更新State
 * 3. View接收到State变化刷新UI
 *
 */
class MainBaseActivity : BaseActivity<ActivityMainBinding>() {

    override fun getBinding(inflater: LayoutInflater): ActivityMainBinding =
        ActivityMainBinding.inflate(inflater)

    override fun flowData() {

    }

    override fun flowView() {
        bd.buttonOne.onClick {
            startAc(CoroutinesActivity::class.java)
        }
        bd.mainRetrofit.onClick {
            startAc(RetrofitActivity::class.java)
        }
        bd.mainBus.onClick {
            startAc(ChannelBusActivity::class.java)
        }
        ChannelBus.bus.receive(lifecycleOwner = this, context =  Dispatchers.Main) {
            bd.mainBus.text = "收到数据"
        }
    }
}

