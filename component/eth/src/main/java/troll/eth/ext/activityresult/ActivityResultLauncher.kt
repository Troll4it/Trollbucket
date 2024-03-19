package troll.eth.ext.activityresult

import android.app.Activity
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.core.app.ActivityOptionsCompat
import com.blankj.utilcode.util.ActivityUtils
import troll.eth.ext.intentOf


fun ActivityResultLauncher<Unit>.launch(options: ActivityOptionsCompat? = null) =
    launch(Unit, options)

inline fun <reified T : Activity> ActivityResultLauncher<Intent>.launch(vararg pairs: Pair<String, *>) =
    launch(ActivityUtils.getTopActivity().intentOf<T>(*pairs))