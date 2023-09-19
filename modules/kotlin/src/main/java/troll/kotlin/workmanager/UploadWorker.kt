package troll.kotlin.workmanager

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.ForegroundInfo
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay
import troll.kotlin.R
import troll.kotlin.jetpack.JetpackActivity

class UploadWorker : CoroutineWorker {
    constructor(appContext: Context, params: WorkerParameters) : super(appContext, params)

    private lateinit var mNotificationBuilder: NotificationCompat.Builder

    companion object {

        const val TAG = "DownloadCoroutineWorker"
        const val INPUT_KEY = "INPUT_KEY"
        const val OUTPUT_KEY = "OUTPUT_KEY"
        private const val START_DOWNLOAD_NOTIFICATION_ID = 435345123
        private const val DOWNLOAD_SUCCEED_NOTIFICATION_ID = 435345235

    }

    override suspend fun doWork(): Result {
        val data = fakeDownload()
        showSuccessNotification()
        val outData = Data.Builder().putString(OUTPUT_KEY, data).build()
        return Result.success(outData)
    }

    override suspend fun getForegroundInfo(): ForegroundInfo {
        Log.i(TAG, "Thread:   getForegroundInfo getForegroundInfo")
        val context = applicationContext
        return ForegroundInfo(START_DOWNLOAD_NOTIFICATION_ID, createNotification(context) {
            setContentTitle("Start Download")
            setSmallIcon(R.drawable.custom_bg_top)
            setContentText("Start Download ${inputData.getString(INPUT_KEY)}")
            priority = NotificationCompat.PRIORITY_DEFAULT
            val cancel = WorkManager.getInstance(context).createCancelPendingIntent(id)
            //设置cancelWork按钮
            addAction(R.drawable.ic_shortcut_cash, "Cancel", cancel)
            mNotificationBuilder = this
        })
    }

    private suspend fun fakeDownload(): String {
        Log.i(TAG, "Thread:${Thread.currentThread().name}")
        for (i in 0..100) {
            delay(100L)
            mNotificationBuilder.setContentText("Start Download ${inputData.getString(INPUT_KEY)} $i%")
            notifyNotification(
                applicationContext, START_DOWNLOAD_NOTIFICATION_ID, mNotificationBuilder.build()
            )
        }
        Log.i(TAG, "Download Succeed")
        return "Download Succeed"
    }

    private fun showSuccessNotification() {
        notifyNotification(applicationContext, DOWNLOAD_SUCCEED_NOTIFICATION_ID) {
            setContentTitle("Download Succeed")
            setSmallIcon(R.drawable.icon_click_circle_show)
            setContentText("Download ${inputData.getString(INPUT_KEY)} Succeed")
            priority = NotificationCompat.PRIORITY_DEFAULT
            setAutoCancel(true)
            val intent = Intent(applicationContext, JetpackActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(
                applicationContext, 0, intent, PendingIntent.FLAG_IMMUTABLE
            )
            setContentIntent(pendingIntent)
        }
    }
}