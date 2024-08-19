package troll.kotlin.flow

import android.view.LayoutInflater
import androidx.activity.viewModels
import troll.btc.extensions.observe
import troll.btc.extensions.onClick
import troll.btc.log.TLog
import troll.eth.base.viewbinding.BaseActivity
import troll.kotlin.databinding.ActivityFlowBinding

class FlowActivity : BaseActivity<ActivityFlowBinding>() {

    private val vm: FlowViewModel by viewModels()
    override fun getBinding(inflater: LayoutInflater): ActivityFlowBinding =
        ActivityFlowBinding.inflate(inflater)

    override fun flowData() {

        bd.kotlinFlowFlow.onClick {
            vm.getData().observe(this) {
                TLog.i("获取到数据${lifecycle.currentState}", "Flow")
            }
        }


    }

    override fun flowView() {
    }
}

