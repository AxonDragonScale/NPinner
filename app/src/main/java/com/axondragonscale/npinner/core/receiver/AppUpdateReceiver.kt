package com.axondragonscale.npinner.core.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.axondragonscale.npinner.core.NPinnerNotificationMonitor
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Ronak Harkhani on 01/05/23
 */

@AndroidEntryPoint
class AppUpdateReceiver : BroadcastReceiver() {

    @Inject lateinit var notificationMonitor: NPinnerNotificationMonitor

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action != Intent.ACTION_MY_PACKAGE_REPLACED) return

        GlobalScope.launch {
            notificationMonitor.ensurePinnedNotificationVisibility()
        }
    }

}
