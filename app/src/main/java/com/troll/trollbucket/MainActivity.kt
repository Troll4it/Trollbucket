package com.troll.trollbucket

import android.view.LayoutInflater
import androidx.activity.viewModels
import com.troll.trollbucket.databinding.ActivityMainBinding
import troll.eth.base.ActivityEx


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


}