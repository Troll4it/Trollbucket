package troll.asm/** * author : TangPeng * date : 6/6/22 14:13 * description : */object TimeCache {    private val startTimes = mutableMapOf<String, Long>()    private val endTimes = mutableMapOf<String, Long>()    public fun putEndTime(methodName: String) {        endTimes[methodName] = System.currentTimeMillis()    }    public fun putStartTime(methodName: String) {        startTimes[methodName] = System.currentTimeMillis()    }    public fun printlnTime(methodName: String) {        if (startTimes.containsKey(methodName).not() || endTimes.containsKey(methodName).not()) {            println("方法methodName：$methodName 不存在")        }        val currTime = startTimes[methodName]?.let { endTimes[methodName]?.minus(it) }        println("方法：$methodName 耗时：$currTime")    }}