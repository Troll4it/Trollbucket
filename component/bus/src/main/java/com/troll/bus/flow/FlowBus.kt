package com.troll.bus.flowimport androidx.lifecycle.LifecycleOwnerimport com.troll.bus.BaseBusimport com.troll.bus.Eventimport kotlinx.coroutines.CoroutineScopeimport kotlinx.coroutines.Dispatchersimport kotlinx.coroutines.MainScopeimport kotlinx.coroutines.channels.BufferOverflowimport kotlinx.coroutines.flow.MutableSharedFlowimport kotlinx.coroutines.launchclass FlowBus : BaseBus() {    private val scope: CoroutineScope = MainScope()    private val flow = MutableSharedFlow<Event<*>>(        replay = 0,        extraBufferCapacity = 0,        onBufferOverflow = BufferOverflow.SUSPEND    )    override fun send(event: Event<*>) {        scope.launch(Dispatchers.Default) {            flow.emit(event)        }    }    override fun createObserver(owner: LifecycleOwner): Observer {        return Ob    }    override fun createObserver(scope: CoroutineScope): Observer {        TODO("Not yet implemented")    }    override fun createObserver(): Observer {        TODO("Not yet implemented")    }    override fun createObserver(count: Int): Observer {        TODO("Not yet implemented")    }    override fun release() {        TODO("Not yet implemented")    }    private     internal inner class ObserverImpl(        private val scope: CoroutineScope?,        private val takeCount: Int = -1    ) : Observer {        override fun <T> onValue(eventId: Int, action: (T) -> Unit) {            this@FlowBus.onV        }        override fun <T> onNullableValue(eventId: Int, action: (T?) -> Unit) {            TODO("Not yet implemented")        }        override fun <R : Event<*>> onEvent(eventId: Int, action: (R) -> Unit) {            TODO("Not yet implemented")        }        override fun onMessage(eventId: Int, action: () -> Unit) {            TODO("Not yet implemented")        }    }}