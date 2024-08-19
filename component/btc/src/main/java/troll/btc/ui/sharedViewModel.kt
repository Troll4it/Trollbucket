package troll.btc.ui

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@MainThread
inline fun <reified VM : ViewModel> LifecycleOwner.sharedViewModel(
    viewModelClass: Class<VM> = VM::class.java,
    noinline keyFactory: (() -> String)? = null,
    noinline factoryProducer: (() -> ViewModelProvider.Factory)? = null,
): Lazy<VM> {
    return SharedViewModelLazy(
        viewModelClass,
        keyFactory,
        { this },
        factoryProducer ?: { ViewModelProvider.NewInstanceFactory() }
    )
}

@PublishedApi
internal class SharedViewModelLazy<VM : ViewModel>(
    private val viewModelClass: Class<VM>,
    private val keyFactory: (() -> String)?,
    private val lifecycleProducer: () -> LifecycleOwner,
    private val factoryProducer: () -> ViewModelProvider.Factory,
) : Lazy<VM> {
    private var cached: VM? = null
    override val value: VM
        get() {
            return cached ?: kotlin.run {
                val factory = factoryProducer()
                if (keyFactory != null) {
                    GlobalViewModelProvider(factory).get(
                        lifecycleProducer().lifecycle,
                        keyFactory.invoke(),
                        viewModelClass
                    )
                } else {
                    GlobalViewModelProvider(factory).get(
                        lifecycleProducer().lifecycle,
                        viewModelClass
                    )
                }.also {
                    cached = it
                }
            }
        }

    override fun isInitialized() = cached != null
}
