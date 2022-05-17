package com.troll.trollbucket

import android.view.LayoutInflater
import com.troll.bus.channel.ChannelBus
import com.troll.trollbucket.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import troll.btc.extensions.onClick
import troll.btc.extensions.startAc
import troll.eth.base.viewbinding.BaseActivity
import troll.kotlin.animation.AnimationDetailActivity
import troll.kotlin.flow.FlowActivity
import troll.kotlin.coroutines.CoroutinesActivity
import troll.kotlin.dag.DAGActivity
import troll.kotlin.shortCut.ShortCutActivity
import troll.kotlin.softinput.SoftInputActivity
import wan.main.WanActivity


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
        }
        bd.mainBus.onClick {
            startAc(FlowActivity::class.java)
        }
        bd.mainWanAndroid.onClick {
            startAc(WanActivity::class.java)

        }
        bd.mainDag.onClick {
            startAc(DAGActivity::class.java)
        }
        bd.mainSoftInput.onClick {
            startAc(SoftInputActivity::class.java)
        }
        bd.mainCompose.onClick {
//            startAc(ComposeActivity::class.java)
        }
        bd.mainAnimation.onClick {
            startAc(AnimationDetailActivity::class.java)
        }
        bd.mainShortCut.onClick {
            startAc(ShortCutActivity::class.java)
        }
        ChannelBus.bus.receive(lifecycleOwner = this, context = Dispatchers.Main) {
            bd.mainBus.text = "收到数据"
        }
    }
}

