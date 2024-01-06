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
class UnpinNotificationReceiver : BroadcastReceiver() {

    companion object {
        private const val UNPIN_ACTION = "npinner.unpin_notification"
        private const val EXTRA_NOTIFICATION_ID = "notificationId"

        fun getIntent(context: Context, notificationId: String) =
            Intent(context, UnpinNotificationReceiver::class.java).apply {
                action = UNPIN_ACTION
                putExtra(EXTRA_NOTIFICATION_ID, notificationId)
            }
    }

    @Inject lateinit var repository: NotificationRepository
    @Inject lateinit var notificationManager: NPinnerNotificationManager

    override fun onReceive(context: Context?, intent: Intent?) {
        val notificationId = intent?.getStringExtra(EXTRA_NOTIFICATION_ID)
        if (notificationId == null || intent.action != UNPIN_ACTION) return

        notificationManager.dismissNotification(notificationId)

        // TODO: Write NPinner Dispatcher
        GlobalScope.launch {
            repository.updatePinStatus(notificationId, false)
        }
    }

}
