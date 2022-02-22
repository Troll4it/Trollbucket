package com.troll.trollbucket

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.view.LayoutInflater
import androidx.core.animation.addListener
import com.troll.trollbucket.databinding.ActivityMainBinding
import troll.eth.base.viewbinding.BaseActivity
import troll.kotlin.activityResultContracts.ResultContractsActivity


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
        val vertical: ObjectAnimator = ObjectAnimator.ofFloat(bd.shadeView, "vertical", 0f, 200f)
        val horizontal: ObjectAnimator =
            ObjectAnimator.ofFloat(bd.shadeView, "horizontal", 0f, 200f)
        val set = AnimatorSet()
        set.playTogether(vertical, horizontal)
        set.duration = 2000
        set.start()

        set.addListener(onEnd = {
            println("动画完成 回调")
        })

        bd.tv.setOnClickListener {
            startActivity(Intent(this, ResultContractsActivity::class.java))
        }
    }


    fun setDefaultAlias(context: Context) {
        val packageManager = packageManager
//        val cn1 = ComponentName(context, "com.troll.trollbucket.SwitchAliasActivity")
//        packageManager.setComponentEnabledSetting(
//            cn1,
//            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
//            PackageManager.DONT_KILL_APP
//        )

        val cn2 = ComponentName(context, "com.troll.trollbucket.SwitchAliasOneActivity")
        packageManager.setComponentEnabledSetting(
            cn2,
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            PackageManager.DONT_KILL_APP
        )

    }

    fun setAlias2(context: Context) {
        val packageManager = packageManager
        val cn1 = ComponentName(context, "com.troll.trollbucket.SwitchAliasActivity")
        packageManager.setComponentEnabledSetting(
            cn1,
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            PackageManager.DONT_KILL_APP
        )

        val cn2 = ComponentName(context, "com.troll.trollbucket.SwitchAliasOneActivity")
        packageManager.setComponentEnabledSetting(
            cn2,
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            PackageManager.DONT_KILL_APP
        )
    }

}

class TestInvoke() {

    operator fun invoke(): String {
        return "测试 invoke操作符"
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val testInvoke = TestInvoke()

            mapOf<String, String>("" to "")

            testInvoke.invoke()
            println(testInvoke())
        }
    }
}


