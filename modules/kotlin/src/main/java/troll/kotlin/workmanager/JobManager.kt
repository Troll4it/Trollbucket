package troll.kotlin.workmanager

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context


fun initJobInfo(context: Context) {
    val jobInfo = JobInfo.Builder(
        1, ComponentName(
            context.applicationContext.packageName, DemoJobService::class.java.name
        )
    ).setBackoffCriteria(100, JobInfo.BACKOFF_POLICY_LINEAR) // 重试机制
        .setMinimumLatency(1000)// 设置延迟时间
        .setOverrideDeadline(100000)// 设置最后延期，如果达到这个点没有执行，会被强制执行一次
        .setPersisted(true) // 持久化
        .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED).setRequiresCharging(false)
        .setRequiresDeviceIdle(false).build()

    val jobScheduler = context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
    jobScheduler.schedule(jobInfo)
}