package troll.kotlin.workmanager

import android.app.job.JobParameters
import android.app.job.JobService
import troll.btc.log.TLog

class DemoJobService : JobService() {
    override fun onStartJob(params: JobParameters?): Boolean {
        TLog.i(message = "执行 onStartJob")
        return false
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        TLog.i(message = "执行 onStopJob")
        return false
    }
}