package troll.eth.ext

import android.content.Context
import com.blankj.utilcode.util.Utils
import java.io.File

inline val externalCacheDirPath: String? get() = Utils.getApp().externalCacheDirPath
inline val Context.externalCacheDirPath: String? get() = externalCacheDir?.absolutePath
inline val fileSeparator: String get() = File.separator

