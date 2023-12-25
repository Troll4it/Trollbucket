package troll.kotlin.agency

import androidx.lifecycle.DefaultLifecycleObserver

class BaseAgencyImpl : BaseAgency, DefaultLifecycleObserver {
    override fun print() {
        println("打印")
    }

}