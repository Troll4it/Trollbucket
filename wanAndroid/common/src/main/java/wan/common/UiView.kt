package wan.common/** * author : TangPeng * date : 2/1/23 15:30 * description : */import androidx.lifecycle.LifecycleOwnerimport androidx.lifecycle.lifecycleScopeimport kotlinx.coroutines.Dispatchersimport kotlinx.coroutines.flow.*import kotlinx.coroutines.launchinterface UiView : LifecycleOwner {    fun showLoading()    fun dismissLoading()}fun UiView.launchWithLoading(requestBlock: suspend () -> Unit) {    lifecycleScope.launch {        flow {            emit(requestBlock())        }.flowOn(Dispatchers.IO)            .onStart {                showLoading()            }.onCompletion {                dismissLoading()            }.collect(NopCollector)    }}internal object NopCollector : FlowCollector<Any?> {    override suspend fun emit(value: Any?) {        // does nothing    }}