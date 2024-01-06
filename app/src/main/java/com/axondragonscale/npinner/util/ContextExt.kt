package com.axondragonscale.npinner.util

import android.app.NotificationManager
import android.content.Context

/**
 * Created by Ronak Harkhani on 01/05/23
 */

val Context.systemNotificationManager: NotificationManager
    get() = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
