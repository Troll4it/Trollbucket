package troll.btc.ui

import android.os.Build
import android.text.TextUtils
import android.util.Log
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.reflect.Method


/**
 * 如果要判断单个手机，需要在这里找
 * https://support.google.com/googleplay/answer/1727131?hl=en
 */
//是否是荣耀设备
fun isHonor() = Build.MANUFACTURER.equals("HONOR", ignoreCase = true)

//是否是小米设备
fun isXiaomi() = Build.MANUFACTURER.equals("Xiaomi", ignoreCase = true)

//判断是否是小米12s的机型
fun isXiaomi12S() = isXiaomi() && Build.MODEL.contains("2206123SC") //xiaomi 12s

//是否是oppo设备
//realme 是oppo的海外品牌后面脱离了；一加是oppo的独立运营品牌。因此判断
//它们是需要单独判断
fun isOppo() = Build.MANUFACTURER.equals("OPPO", ignoreCase = true)

//是否是一加手机
fun isOnePlus() = Build.MANUFACTURER.equals("OnePlus", ignoreCase = true)

//是否是realme手机
fun isRealme() = Build.MANUFACTURER.equals("realme", ignoreCase = true)

//是否是vivo设备
fun isVivo() = Build.MANUFACTURER.equals("vivo", ignoreCase = true)

//是否是华为设备
fun isHuawei() = Build.MANUFACTURER.equals("HUAWEI", ignoreCase = true)

private const val HARMONY_OS = "harmony"

/**
 *
 * @return true if it is harmony os
 */
fun isHarmonyOS(): Boolean {
    try {
        val clz = Class.forName("com.huawei.system.BuildEx")
        val method: Method = clz.getMethod("getOsBrand")
        return HARMONY_OS == method.invoke(clz)
    } catch (e: ClassNotFoundException) {
        Log.e("Rom", "occured ClassNotFoundException")
    } catch (e: NoSuchMethodException) {
        Log.e("Rom", "occured NoSuchMethodException")
    } catch (e: Exception) {
        Log.e("Rom", "occur other problem")
    }
    return false
}

fun checkIsMiui() = !TextUtils.isEmpty(getSystemProperty("ro.miui.ui.version.name"))

private fun getSystemProperty(propName: String): String? {
    val line: String
    var input: BufferedReader? = null
    try {
        val p = Runtime.getRuntime().exec("getprop $propName")
        input = BufferedReader(InputStreamReader(p.inputStream), 1024)
        line = input.readLine()
        input.close()
    } catch (ex: IOException) {
        Log.i("TAG", "Unable to read sysprop $propName", ex)
        return null
    } finally {
        if (input != null) {
            try {
                input.close()
            } catch (e: IOException) {
                Log.i("TAG", "Exception while closing InputStream", e)
            }
        }
    }
    return line
}

fun checkIsEmuiOrMagicUI(): Boolean {
    return if (Build.VERSION.SDK_INT >= 31) {
        //官方方案，但是只适用于api31以上（Android 12）
        try {
            val clazz = Class.forName("com.hihonor.android.os.Build")
            Log.d("TAG", "clazz = " + clazz)
            true
        } catch (e: ClassNotFoundException) {
            Log.d("TAG", "no find class")
            e.printStackTrace()
            false
        }
    } else {
        //网上方案，测试了 荣耀畅玩8C
        // 荣耀20s、荣耀x40 、荣耀v30 pro 四台机型，均可正常判断
        !TextUtils.isEmpty(getSystemProperty("ro.build.version.emui"))
    }
}

//这段代码是错的
fun checkIsColorOs() = !TextUtils.isEmpty(getSystemProperty("ro.build.version.opporom"))

//网上代码，在 IQOQ Neo5、vivo Y50、 vivo x70三种机型上
//都可以正常判断
fun checkIsOriginOs() = !TextUtils.isEmpty(getSystemProperty("ro.vivo.os.version"))