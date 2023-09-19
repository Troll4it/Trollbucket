package troll.kotlin.workmanager

import android.view.LayoutInflater
import android.view.View
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkInfo
import androidx.work.WorkManager
import troll.btc.extensions.onClick
import troll.eth.base.viewbinding.BaseActivity
import troll.kotlin.databinding.ActivityWorkManagerBinding

class WorkManagerActivity : BaseActivity<ActivityWorkManagerBinding>() {

    private val mWorkManager: WorkManager by lazy(LazyThreadSafetyMode.NONE) {
        WorkManager.getInstance(this)
    }

    override fun getBinding(inflater: LayoutInflater): ActivityWorkManagerBinding =
        ActivityWorkManagerBinding.inflate(inflater)

    override fun flowData() {
        mWorkManager.getWorkInfosByTagLiveData(UploadWorker.TAG).observe(this) {
                if (it.isNotEmpty()) {
                    val workInfo = it[0]
                    when (workInfo.state) {
                        WorkInfo.State.SUCCEEDED -> {
                            bd.download.setText("重新下载")
                            bd.tips.visibility = View.VISIBLE
                            bd.tips.text = workInfo.outputData.getString(UploadWorker.OUTPUT_KEY)
                            bd.download.setOnClickListener {
                                enqueueDownloadWork()
                            }
                        }

                        WorkInfo.State.BLOCKED,
                        WorkInfo.State.ENQUEUED,
                        WorkInfo.State.RUNNING,
                        -> {
                            bd.tips.text = "正在下载"
                            bd.download.text = "取消下载"
                            bd.download.setOnClickListener {
                                mWorkManager.cancelUniqueWork(UploadWorker.TAG)
                            }
                        }

                        WorkInfo.State.CANCELLED -> {
                            bd.tips.visibility = View.GONE
                            bd.download.text = "开始下载"
                            bd.download.setOnClickListener {
                                enqueueDownloadWork()
                            }
                        }
                    }
                }
            }
    }

    override fun flowView() {
        bd.download.onClick {
            initJobInfo(this)
//            enqueueDownloadWork()
        }
    }

    private fun enqueueDownloadWork() {
        val downloadContent = "复仇者联盟"
        val constraints =
            Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val data = Data.Builder().putString(UploadWorker.INPUT_KEY, downloadContent).build()
        mWorkManager.beginUniqueWork(
            UploadWorker.TAG,
            ExistingWorkPolicy.REPLACE,
            OneTimeWorkRequestBuilder<UploadWorker>().addTag(UploadWorker.TAG).setInputData(data)
                .setConstraints(constraints)
                .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)//
                .build()
        ).enqueue()
    }

}