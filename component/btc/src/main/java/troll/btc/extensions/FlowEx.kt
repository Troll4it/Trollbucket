package troll.btc.extensionsimport androidx.lifecycle.Lifecycleimport androidx.lifecycle.LifecycleOwnerimport androidx.lifecycle.lifecycleScopeimport androidx.lifecycle.repeatOnLifecycleimport kotlinx.coroutines.flow.*import kotlinx.coroutines.launchimport kotlin.reflect.KProperty1/** * author : TangPeng * description : Flow 的拓展 */internal data class StateTuple1<A>(val a: A)fun <T> MutableStateFlow<T>.setState(reducer: T.() -> T) {    this.value = this.value.reducer()}fun <T, A> StateFlow<T>.observeState(    lifecycleOwner: LifecycleOwner,    property1: KProperty1<T, A>,    action: (A) -> Unit) {    lifecycleOwner.lifecycleScope.launch {        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {            this@observeState.map {                StateTuple1(property1.get(it))            }.distinctUntilChanged()                .collect { (a) ->   //                    action.invoke(a)                }        }    }}typealias SharedFlowEvents<T> = MutableSharedFlow<List<T>>@Suppress("FunctionName")fun <T> SharedFlowEvents(): SharedFlowEvents<T> {    return MutableSharedFlow()}fun <T> SharedFlow<List<T>>.observeEvent(lifecycleOwner: LifecycleOwner, action: (T) -> Unit) {    lifecycleOwner.lifecycleScope.launchWhenStarted {        this@observeEvent.collect {            it.forEach { event ->                action.invoke(event)            }        }    }}// todosuspend fun <T> SharedFlowEvents<T>.setEvent(vararg values: T) {    val eventList = values.toList()    this.emit(eventList)}