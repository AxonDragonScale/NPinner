package com.axondragonscale.npinner.core

import android.content.Context
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.axondragonscale.npinner.model.NPinnerNotification
import com.axondragonscale.npinner.work.schedule.ScheduleNotificationRequest
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Ronak Harkhani on 01/05/23
 */

@Singleton
class NPinnerNotificationScheduler @Inject constructor(
    @ApplicationContext private val context: Context,
) {

    fun scheduleNotification(notification: NPinnerNotification) {
        with(notification) {
            schedule ?: return

            val scheduledAt = schedule.asLocalDateTime()
            val currentTime = LocalDateTime.now()
            if (scheduledAt.isBefore(currentTime)) return

            WorkManager.getInstance(context)
                .enqueueUniqueWork(
                    ScheduleNotificationRequest.getTag(id),
                    ExistingWorkPolicy.REPLACE,
                    ScheduleNotificationRequest.getRequest(id, schedule)
                )
        }
    }

    fun cancelScheduledNotification(id: String) {
        WorkManager.getInstance(context)
            .cancelAllWorkByTag(ScheduleNotificationRequest.getTag(id))
    }

}
