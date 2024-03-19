package troll.eth.ext.activityresult

import android.net.Uri
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultCaller
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityOptionsCompat
import troll.eth.ext.EXTERNAL_MEDIA_IMAGES_URI
import troll.eth.ext.externalCacheDirPath
import troll.eth.ext.fileSeparator
import troll.eth.ext.toUri
import java.io.File

fun ActivityResultCaller.registerForStartActivityResult(callback: ActivityResultCallback<ActivityResult>) =
    registerForActivityResult(ActivityResultContracts.StartActivityForResult(), callback)

fun ActivityResultCaller.registerForTakePictureResult(callback: ActivityResultCallback<Boolean>) =
    SaveToUriLauncher(
        registerForActivityResult(ActivityResultContracts.TakePicture(), callback),
        EXTERNAL_MEDIA_IMAGES_URI,
        "jpg"
    )

class SaveToUriLauncher(
    launcher: ActivityResultLauncher<Uri>,
    private val uri: Uri,
    private val suffixes: String
) :
    DecorActivityResultLauncher<Uri>(launcher) {
    fun launchAndSaveToCache() =
        File("$externalCacheDirPath$fileSeparator${System.currentTimeMillis()}.$suffixes").toUri()
            .also { launch(it) }
}

abstract class DecorActivityResultLauncher<T>(private val launcher: ActivityResultLauncher<T>) :
    ActivityResultLauncher<T>() {

    override fun launch(input: T?, options: ActivityOptionsCompat?) =
        launcher.launch(input, options)

    override fun unregister() = launcher.unregister()

    override fun getContract(): ActivityResultContract<T, *> = launcher.contract
}