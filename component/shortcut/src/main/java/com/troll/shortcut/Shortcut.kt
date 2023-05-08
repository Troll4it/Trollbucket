package com.troll.shortcut

import android.annotation.TargetApi
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.Bitmap
import android.graphics.drawable.Icon
import android.os.Build
import androidx.annotation.DrawableRes

object Shortcut {


    /**
     * 这个类似应用图标的
     */
    fun addPinShortCutManager(
        context: Context, shortCutId: String, title: String, bitmap: Bitmap, intent: Intent
    ) {
        val btp = createBitmap(context, bitmap)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val shortcutManager =
                context.getSystemService(Service.SHORTCUT_SERVICE) as ShortcutManager
            if (shortcutManager.isRequestPinShortcutSupported) {
                val info =
                    ShortcutInfo.Builder(context, shortCutId).setIcon(Icon.createWithBitmap(btp))
                        .setShortLabel(title).setIntent(intent).build()
                shortcutManager.requestPinShortcut(info, null)
            }
        } else {
            val shortcutIntent = Intent(Intent.ACTION_CREATE_SHORTCUT).apply {
                putExtra(Intent.EXTRA_SHORTCUT_NAME, title)
                putExtra(Intent.EXTRA_SHORTCUT_ICON, btp)
                putExtra(Intent.EXTRA_RESTRICTIONS_INTENT, intent)
            }
            context.sendBroadcast(shortcutIntent)
        }
    }


    fun updatePinShortcut(
        context: Context, bitmap: Bitmap, shortCutId: String, title: String, intent: Intent
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val btp = createBitmap(context, bitmap)
            val shortcutManager = context.getSystemService(ShortcutManager::class.java)
            if (shortcutManager.isRequestPinShortcutSupported) {
                val shortcutIntent = Intent(Intent.ACTION_CREATE_SHORTCUT).apply {
                    putExtra(Intent.EXTRA_SHORTCUT_NAME, title)
                    putExtra(Intent.EXTRA_SHORTCUT_ICON, btp)
                    putExtra(Intent.EXTRA_RESTRICTIONS_INTENT, intent)
                }
                val info =
                    ShortcutInfo.Builder(context, shortCutId).setIcon(Icon.createWithBitmap(btp))
                        .setShortLabel(title).setIntent(shortcutIntent).build()
                shortcutManager.updateShortcuts(listOf(info))
            }
        }
    }

    @TargetApi(25)
    fun buildShortInfo(
        context: Context,
        shortId: String,
        title: String,
        @DrawableRes resId: Int,
        intent: Intent
    ): ShortcutInfo {
        return ShortcutInfo.Builder(context, shortId)
            .setShortLabel(title)
            .setLongLabel(title)
            .setIcon(Icon.createWithResource(context, resId))
            .setIntent(intent).build()

    }

    @TargetApi(25)
    fun addDynamicShortcut(context: Context, list: MutableList<ShortcutInfo>) {
        val manager = context.getSystemService(ShortcutManager::class.java)
        kotlin.runCatching {
            manager.setDynamicShortcuts(list)
        }
    }

    /**
     * 创建一个与icon同样大小的widget
     */
    private fun createBitmap(context: Context, bitmap: Bitmap): Bitmap {
        val packageManager = context.packageManager
        val icon =
            packageManager.getApplicationInfo(context.packageName, 0).loadIcon(packageManager)
        return Bitmap.createScaledBitmap(bitmap, icon.intrinsicWidth, icon.intrinsicHeight, true)
    }
}