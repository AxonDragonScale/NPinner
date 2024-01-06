package com.axondragonscale.npinner.core

import android.Manifest
import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.axondragonscale.npinner.model.NPinnerNotification
import com.axondragonscale.npinner.util.systemNotificationManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Ronak Harkhani on 01/05/23
 */

private const val CHANNEL_ID = "NPinner_Pinned_Notifications_Channel"
private const val CHANNEL_NAME = "Pinned Notifications"
private const val CHANNEL_DESC = "Channel for Pinned Notifications"

@Singleton
class NPinnerNotificationManager @Inject constructor(
    @ApplicationContext private val context: Context,
) {

    private val notificationManager = NotificationManagerCompat.from(context)

    @SuppressLint("MissingPermission")
    fun postNotification(notification: NPinnerNotification) {
        createNotificationChannel()

        val systemNotification = notification.toSystemNotification()
        notificationManager.notify(notification.id.hashCode(), systemNotification)
    }

    fun dismissNotification(notification: NPinnerNotification) {
        notificationManager.cancel(notification.id.hashCode())
    }

    private fun NPinnerNotification.toSystemNotification(): Notification {
        TODO()
    }

    private fun hasNotificationPermission() =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            true
        }

    private fun createNotificationChannel() {
        // Notification Channels are available since Oreo
        // Use Compat version to automatically handle it
        val channel = NotificationChannelCompat
            .Builder(CHANNEL_ID, NotificationManagerCompat.IMPORTANCE_LOW)
            .setName(CHANNEL_NAME)
            .setDescription(CHANNEL_DESC)
            .setShowBadge(false)
            .setLightsEnabled(false)
            .build()

        notificationManager.createNotificationChannel(channel)
    }
}
