package troll.btc.extensions

import android.annotation.SuppressLint
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

@SuppressLint("CheckResult")
open class Live<T>(default: T? = null) : MutableLiveData<T>() {

    init {
        if (default != null) {
            value = default
        }
    }

    @Suppress("DEPRECATION")
    open fun observe(owner: LifecycleOwner? = null, block: (T) -> Unit) {
        if (owner == null) {
            observeForever {
                block(it)
            }
        } else {
            observe(owner, Observer {
                block.invoke(it)
            })
        }
    }

    @Deprecated(
        message = "统一使用更便捷的调用方式",
        replaceWith = ReplaceWith("this.observe(observer)"),
        level = DeprecationLevel.WARNING
    )
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, observer)
    }

    @Deprecated(
        message = "统一使用更便捷的调用方式",
        replaceWith = ReplaceWith("this.observe()"),
        level = DeprecationLevel.WARNING
    )
    override fun observeForever(observer: Observer<in T>) {
        super.observeForever(observer)
    }

    /**
     * PS:
     * 声明为T?类型是语法问题导致
     * 如果使用T类型, 会无法调用 value = xxx, 只能setValue(xxx)
     *
     * Error: "Val cannot be reassigned"
     * 实际上编译时并不接受T?的类型, 只会接受T类型
     */
    override fun setValue(value: T?) {
        if (isMainThread()) {
            super.setValue(value)
        } else {
            postValue(value)
        }
    }
}

/**
 * 引起自身数据刷新的回调(应用于改变了其中某些值但数据源又没有改变的情况)
 */
fun <T> MutableLiveData<T>.refresh() {
    value = value
}

fun <T> live(block: () -> T): Live<T> = Live(block())


fun <T> MutableLiveData<T>.asLiveData(): LiveData<T> {
    return this
}
