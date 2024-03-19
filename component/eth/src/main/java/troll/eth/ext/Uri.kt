package troll.eth.ext

import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.core.content.FileProvider
import com.blankj.utilcode.util.Utils
import java.io.File


val fileProviderAuthority: String = "${Utils.getApp().packageName}.provider"


inline val EXTERNAL_MEDIA_IMAGES_URI: Uri
    get() = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

fun File.toUri(authority: String = fileProviderAuthority): Uri =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        FileProvider.getUriForFile(Utils.getApp(), authority, this)
    } else {
        Uri.fromFile(this)
    }
