package troll.btc.extensions.flow

/**
 * 通用包装类：打破StateFlow重复值过滤
 * @param value 实际要传递的数据
 * @param version 版本号（自动递增，确保每次实例不同）
 */
data class WrappedData<T>(
    val value: T,
    private val version: Long = System.currentTimeMillis() // 用时间戳/自增ID做版本号
)