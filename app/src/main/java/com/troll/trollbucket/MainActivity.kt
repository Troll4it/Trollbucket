package com.troll.trollbucket

import android.content.Intent
import android.view.LayoutInflater
import androidx.activity.viewModels
import com.troll.trollbucket.databinding.ActivityMainBinding
import troll.eth.base.viewbinding.ActivityEx
import troll.mvi.MviActivity


/**
 * MVI:
 *
 * 1. 用户操作Intent的形式，来通知Model
 * 2. Model基于Intent的形式来更新State
 * 3. View接收到State变化刷新UI
 *
 */
class MainActivity : ActivityEx<ActivityMainBinding>() {


    private val vm: MainViewModel by viewModels()

    override fun getBinding(inflater: LayoutInflater): ActivityMainBinding = ActivityMainBinding.inflate(inflater)

    override fun flowData() {

    }

    override fun flowView() {
        binding.tv.setOnClickListener {
            startActivity(Intent(this,MviActivity::class.java))
        }

    }


}