package troll.eth.ext

import android.app.Activity
import android.content.Intent
import androidx.core.os.bundleOf

fun Activity.finishWithResult(vararg pairs: Pair<String, *>) {
    setResult(Activity.RESULT_OK, Intent().putExtras(bundleOf(*pairs)))
    finish()
}