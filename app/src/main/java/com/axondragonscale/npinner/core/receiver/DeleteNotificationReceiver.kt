package com.axondragonscale.npinner.core.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.axondragonscale.npinner.core.NPinnerNotificationManager
import com.axondragonscale.npinner.repository.NotificationRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Ronak Harkhani on 01/05/23
 */

@AndroidEntryPoint
class DeleteNotificationReceiver : BroadcastReceiver() {

    companion object {
        private const val DELETE_ACTION = "npinner.delete_notification"
        private const val EXTRA_NOTIFICATION_ID = "notificationId"

        fun getIntent(context: Context, notificationId: String) =
            Intent(context, DeleteNotificationReceiver::class.java).apply {
                action = DELETE_ACTION
                putExtra(EXTRA_NOTIFICATION_ID, notificationId)
            }
    }

    @Inject lateinit var repository: NotificationRepository
    @Inject lateinit var notificationManager: NPinnerNotificationManager

    override fun onReceive(context: Context?, intent: Intent?) {
        val notificationId = intent?.getStringExtra(EXTRA_NOTIFICATION_ID)
        if (notificationId == null || intent.action != DELETE_ACTION) return

        notificationManager.dismissNotification(notificationId)

        // TODO: Write NPinner Dispatcher
        GlobalScope.launch {
            repository.delete(repository.getNotification(notificationId))
        }
    }

}
