package troll.kotlin.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import troll.btc.log.TLog
import troll.eth.base.viewbinding.BaseViewModel

class FlowViewModel : BaseViewModel() {

    fun getData(): Flow<String> = flow {
        TLog.i("开始获取数据", "Flow")
        delay(4000)
        emit("1")
        TLog.i("发送数据", "Flow")
    }
}