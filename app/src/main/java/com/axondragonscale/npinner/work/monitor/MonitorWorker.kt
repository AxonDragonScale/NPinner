package com.axondragonscale.npinner.work.monitor

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.axondragonscale.npinner.core.NPinnerNotificationMonitor
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

/**
 * Created by Ronak Harkhani on 15/05/23
 */

@HiltWorker
class MonitorWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val notificationMonitor: NPinnerNotificationMonitor,
) : CoroutineWorker(context, params) {
    
    override suspend fun doWork(): Result {
        notificationMonitor.ensurePinnedNotificationVisibility()
        return Result.success()
    }
    
}
