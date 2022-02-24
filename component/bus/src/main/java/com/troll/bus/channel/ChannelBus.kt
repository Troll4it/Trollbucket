package com.troll.bus.channelimport androidx.lifecycle.Lifecycleimport androidx.lifecycle.LifecycleObserverimport androidx.lifecycle.LifecycleOwnerimport androidx.lifecycle.OnLifecycleEventimport kotlinx.coroutines.Dispatchersimport kotlinx.coroutines.GlobalScopeimport kotlinx.coroutines.Jobimport kotlinx.coroutines.channels.Channelimport kotlinx.coroutines.launchimport java.util.concurrent.ConcurrentHashMapimport kotlin.coroutines.CoroutineContextclass ChannelBus : LifecycleObserver {    private val channel: Channel<Events> = Channel(Channel.BUFFERED)    private val consumerMap = ConcurrentHashMap<String, ChannelConsumer>()    private val lifecycleOwnerMap = ConcurrentHashMap<LifecycleOwner, ChannelConsumer>()    private val stickyEventList = mutableListOf<Events>()    companion object {        val bus: ChannelBus by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {            ChannelBus()        }    }    init {        GlobalScope.launch {            for (e in channel) {                consumerMap.entries.forEach {                    it.value.jobList.add(launch(it.value.context) {                        it.value.event(e)                    })                }                lifecycleOwnerMap.entries.forEach {                    it.value.jobList.add(launch(it.value.context) {                        it.value.event(e)                    })                }            }        }    }    fun send(event: Events, isSticky: Boolean = false) {        GlobalScope.launch {            if (isSticky) {                stickyEventList.add(event)            }            channel.send(event)        }    }    fun receive(        lifecycleOwner: LifecycleOwner,        context: CoroutineContext,        onEvent: suspend (event: Events) -> Unit    ) {        lifecycleOwner.lifecycle.addObserver(this)        lifecycleOwnerMap[lifecycleOwner] = ChannelConsumer(context, onEvent)    }    fun receiveSticky(        key: String,        context: CoroutineContext = Dispatchers.Main,        onEvent: suspend (event: Events) -> Unit    ) {        consumerMap[key] = ChannelConsumer(context, onEvent)        stickyEventList.forEach {            consumerMap[key]?.jobList?.add(GlobalScope.launch(context) {                onEvent(it)            })        }    }    fun removeStickEvent(event: Events) {        stickyEventList.remove(event)    }    fun remove(key: String) {        consumerMap[key]?.jobList?.forEach {            it.cancel()        }        consumerMap.remove(key)    }    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)    fun remove(lifecycleOwner: LifecycleOwner) {        lifecycleOwnerMap[lifecycleOwner]?.jobList?.forEach {            it.cancel()        }        lifecycleOwnerMap.remove(lifecycleOwner)    }    data class ChannelConsumer(        val context: CoroutineContext,        var event: suspend (event: Events) -> Unit,        var jobList: MutableList<Job> = mutableListOf()    )}enum class Events {    EVENT_1, EVENT_2, EVENT_3}