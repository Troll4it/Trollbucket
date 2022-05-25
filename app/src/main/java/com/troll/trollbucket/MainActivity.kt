package com.troll.trollbucket

import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.troll.trollbucket.databinding.ActivityMainBinding
import troll.btc.extensions.startAc
import troll.eth.base.viewbinding.BaseActivity


/**
 * MVI:
 *
 * 1. 用户操作Intent的形式，来通知Model
 * 2. Model基于Intent的形式来更新State
 * 3. View接收到State变化刷新UI
 *
 */
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val vm: MainViewModel by viewModels()

    private val adapter by lazy {
        MainAdapter {
            vm.processActivity(it.type)
        }
    }

    override fun getBinding(inflater: LayoutInflater): ActivityMainBinding =
        ActivityMainBinding.inflate(inflater)

    override fun flowData() {
        vm.data()
        vm.live.observe(this) {
            adapter.submitList(it)
        }
        vm.liveActivity.observe(this) {
            startAc(it)
        }

    }

    override fun flowView() {
        bd.mainRv.layoutManager = LinearLayoutManager(this)
        bd.mainRv.adapter = adapter
    }
}

