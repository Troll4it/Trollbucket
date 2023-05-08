package troll.kotlin.view

import android.view.LayoutInflater
import troll.eth.base.viewbinding.BaseActivity
import troll.kotlin.databinding.ActivityCustomViewBinding

class CustomViewActivity : BaseActivity<ActivityCustomViewBinding>() {
    override fun getBinding(inflater: LayoutInflater): ActivityCustomViewBinding =
        ActivityCustomViewBinding.inflate(inflater)

    override fun flowData() {
    }

    override fun flowView() {
        bd.customPick.startAnimation()
    }
}