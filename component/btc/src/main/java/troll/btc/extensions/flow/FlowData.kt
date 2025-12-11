package troll.btc.extensions.flow

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FlowData<T>(
    private val initialValue: T,
    private val scope: CoroutineScope,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Main
) {
    private val _stateFlow = MutableStateFlow(initialValue)

    val stateFlow: StateFlow<T> = _stateFlow.asStateFlow()

    /**
     * 安全更新状态（核心方法）
     * @param newValue 新值
     * @param dispatcher 自定义调度线程（默认使用全局默认值）
     * @param beforeUpdate 前置处理逻辑（如校验、格式转换）
     */
    fun update(
        newValue: T,
        dispatcher: CoroutineDispatcher = defaultDispatcher,
        beforeUpdate: (T) -> T = { it } // 前置处理（默认返回原值）
    ) {
        scope.launch(dispatcher) {
            val processedValue = beforeUpdate(newValue)
            _stateFlow.value = processedValue
            println("FlowData update: 最终值=${_stateFlow.value}")

        }
    }

    /**
     * 获取当前值（只读）
     */
    fun getCurrentValue(): T = _stateFlow.value

    /**
     * 重置为初始值
     */
    fun reset() {
        update(initialValue)
    }
}

// 针对字符串的扩展（可选）
fun FlowData<String>.updateWithTrim(newStr: String) {
    // 扩展方法：自动去除首尾空格
    update(newStr) { it.trim() }
}

fun FlowData<String>.updateWithNonEmpty(newStr: String, default: String = "") {
    // 扩展方法：非空校验，空值则使用默认值
    update(newStr) { if (it.isBlank()) default else it }
}