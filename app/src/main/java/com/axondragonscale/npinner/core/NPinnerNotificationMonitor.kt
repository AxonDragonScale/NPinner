package com.axondragonscale.npinner.core

import android.content.Context
import com.axondragonscale.npinner.repository.NotificationRepository
import com.axondragonscale.npinner.util.systemNotificationManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Ronak Harkhani on 01/05/23
 */

@Singleton
class NPinnerNotificationMonitor @Inject constructor(
    @ApplicationContext private val context: Context,
    private val repository: NotificationRepository,
    private val notificationManager: NPinnerNotificationManager,
) {

    suspend fun ensurePinnedNotificationVisibility() {
        val pinnedNotifications = repository.getPinnedNotifications()

        val systemNotificationManager = context.systemNotificationManager
        val activeNotifications = systemNotificationManager.activeNotifications

        pinnedNotifications.forEach { notification ->
            val isVisible = activeNotifications.any { it.id == notification.id.hashCode() }
            if (!isVisible) notificationManager.postNotification(notification)
        }
    }

}
