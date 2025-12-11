package troll.kotlin.flow

import androidx.lifecycle.viewModelScope
import troll.btc.extensions.flow.FlowData
import troll.btc.extensions.flow.WrappedData
import troll.eth.base.viewbinding.BaseViewModel

class FlowViewModel : BaseViewModel() {


    private val responseManager = FlowData(initialValue = WrappedData(""), scope = viewModelScope)
    val responseFlow= responseManager.stateFlow

    fun data() {
        responseManager.update(WrappedData("222222"))
    }
}