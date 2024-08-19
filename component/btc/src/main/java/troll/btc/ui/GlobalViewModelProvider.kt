package troll.btc.ui

import androidx.annotation.MainThread
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore

//todo 解决ViewModelStore 不能调用put方法
class GlobalViewModelProvider(factory: Factory = NewInstanceFactory()) :
    ViewModelProvider(globalStore, factory) {
    companion object {
        private val globalStore = ViewModelStore()
        private val globalLifecycleMap = HashMap<String, MutableSet<Lifecycle>>()
        private const val DEFAULT_KEY = "androidx.lifecycle.ViewModelProvider.DefaultKey"
    }

    @MainThread
    fun <T : ViewModel> get(lifecycle: Lifecycle, modelClass: Class<T>): T {
        val canonicalName = modelClass.canonicalName
            ?: throw IllegalArgumentException("Local and anonymous classes can not be ViewModels")
        return get(lifecycle, "$DEFAULT_KEY:$canonicalName", modelClass)
    }

    @MainThread
    fun <T : ViewModel> get(lifecycle: Lifecycle, key: String, modelClass: Class<T>): T {
        if (lifecycle.currentState == Lifecycle.State.DESTROYED) {
            throw IllegalStateException("Could not get viewmodel when lifecycle was destroyed")
        }
        val viewModel = super.get(key, modelClass)
        val lifecycleList = globalLifecycleMap.getOrElse(key) { mutableSetOf() }
        globalLifecycleMap[key] = lifecycleList
        if (!lifecycleList.contains(lifecycle)) {
            lifecycleList.add(lifecycle)
            lifecycle.addObserver(
                ClearNegativeVMObserver(
                    lifecycle,
                    key,
                    globalStore,
                    globalLifecycleMap
                )
            )
        }
        return viewModel
    }

    private class ClearNegativeVMObserver(
        private val lifecycle: Lifecycle,
        private val key: String,
        private val store: ViewModelStore,
        private val map: HashMap<String, MutableSet<Lifecycle>>,
    ) : LifecycleEventObserver {
        override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
            if (event == Lifecycle.Event.ON_DESTROY) {
                val lifecycleList = map.getOrElse(key) { mutableSetOf() }
                lifecycleList.remove(lifecycle)
                if (lifecycleList.isEmpty()) {
//                    store.put(key, null)
                    map.remove(key)
                }
            }
        }
    }
}
