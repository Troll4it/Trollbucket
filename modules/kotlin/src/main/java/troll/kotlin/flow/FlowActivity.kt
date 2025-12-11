package troll.kotlin.flow

import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
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
            vm.data()
        }

        lifecycleScope.launch {
            vm.responseFlow.collect {
                TLog.i(it.value, "FlowActivity")
            }
        }

    }

    override fun flowView() {
    }
}

