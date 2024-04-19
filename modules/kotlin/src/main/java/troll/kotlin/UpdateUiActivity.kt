package troll.kotlin

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import troll.btc.extensions.onClick
import troll.eth.base.viewbinding.BaseActivity
import troll.kotlin.databinding.ActivityUpdateUiBinding

class UpdateUiActivity : BaseActivity<ActivityUpdateUiBinding>() {
    override fun getBinding(inflater: LayoutInflater): ActivityUpdateUiBinding =
        ActivityUpdateUiBinding.inflate(inflater)

    override fun flowData() {
    }

    override fun flowView() {
        bd.updateUiBtn.onClick {
            Thread {
                Looper.prepare()

                val handler = Looper.myLooper()?.let { Handler(it) }

                handler?.postDelayed(object : Runnable {
                    override fun run() {
                        bd.updateUiBtn.text = "子线程更新成功"
                    }

                }, 5000L)

                Looper.loop()
            }.start()

        }
    }


}