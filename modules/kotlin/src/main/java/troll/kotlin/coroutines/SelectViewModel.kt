package troll.kotlin.coroutinesimport androidx.lifecycle.LifecycleCoroutineScopeimport kotlinx.coroutines.*import kotlinx.coroutines.channels.ReceiveChannelimport kotlinx.coroutines.channels.produceimport kotlinx.coroutines.selects.selectimport troll.eth.base.viewbinding.BaseViewModel/** * author : TangPeng * date : 3/10/23 11:29 * description : */// async并发 select选择最块的结果class SelectViewModel : BaseViewModel() {    fun selectAsync(life: LifecycleCoroutineScope) {        //  灵活选择网络与缓存信息中一个时间最短的//        life.launch {//            val startTime = System.currentTimeMillis()//            val info = select<String> {//                async {//                    getCacheInfo()//                }.onAwait { //使用onAwait{}将执行结果传递给select{}//                    it//                }//                async {//                    getNetInfo()//                }.onAwait {//                    it//                }//            }//            println(info.plus("Time cost: ${System.currentTimeMillis() - startTime}"))//        }        // 如果每次缓存信息比网络信息快，就会出现每次都拿缓存信息，新的解决办法就是在onAwait中        life.launch {            val startTime = System.currentTimeMillis()            val cache = async {                getCacheInfo()            }            val net = async {                getNetInfo()            }            val info = select<String> {                cache.onAwait { //使用onAwait{}将执行结果传递给select{}                    it.plus("旧")                }                net.onAwait {                    it                }            }            println(info.plus("Time cost: ${System.currentTimeMillis() - startTime}"))            if (info.contains("旧")) {                val newInfo = net.await()                println(newInfo.plus("Time cost: ${System.currentTimeMillis() - startTime}"))            }        }    }    fun selectChannel(life: LifecycleCoroutineScope) {        val startTime = System.currentTimeMillis()        life.launch {            val intChannel = produce<Int> {                send(1)                delay(100L)                send(2)                delay(100L)                send(3)                delay(100L)                send(4)                delay(100L)            }            val stringChannel = produce<String> {                send("我")                delay(100L)                send("是")                delay(100L)                send("T")                delay(100L)                send("r")                delay(100L)                send("o")                delay(100L)                send("l")                delay(100L)                send("l")                delay(100L)            }            //            intChannel.consumeEach {//                println("Time cost:  $it")//            }//            stringChannel.consumeEach {//                println("Time cost:  $it")//            }            suspend fun selectChannel(                channel1: ReceiveChannel<Int>, channel2: ReceiveChannel<String>            ): String = select {                channel1.onReceiveCatching {                    it.toString().also {                        println("Time cost: $it")                    }                }                channel2.onReceiveCatching {                    it.also {                        println("Time cost: $it")                    }.toString()                }            }            repeat(3) {                selectChannel(intChannel, stringChannel)            }            println("Time cost: ${System.currentTimeMillis() - startTime}")        }    }    private suspend fun getCacheInfo(): String {        delay(1000)        return "缓存信息"    }    private suspend fun getNetInfo(): String {        delay(2000)        return "网络信息"    }}// 代码段2fun main() = runBlocking {    var i = 0    val jobs = mutableListOf<Job>()    // 重复十次    repeat(10) {        val job = launch(Dispatchers.Default) {            repeat(1000) {                i++            }        }        jobs.add(job)    }    // 等待计算完成    jobs.joinAll()    println("i = $i")}