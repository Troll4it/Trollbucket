package troll.kotlin.coroutinesimport androidx.lifecycle.viewModelScopeimport kotlinx.coroutines.Dispatchersimport kotlinx.coroutines.flow.flowimport kotlinx.coroutines.flow.flowOnimport kotlinx.coroutines.launchimport troll.eth.base.viewbinding.BaseViewModelclass FlowViewModel : BaseViewModel() {    fun testFlow() {        viewModelScope.launch {            flow<Int> { }                .flowOn(Dispatchers.Main)                .collect {                }        }    }}