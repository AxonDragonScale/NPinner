package com.axondragonscale.npinner.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.axondragonscale.npinner.ui.notificationEditor.NotificationEditor
import com.axondragonscale.npinner.ui.notificationEditor.nav.notificationEditorGraph
import com.axondragonscale.npinner.ui.notifications.Notifications
import com.axondragonscale.npinner.ui.notifications.nav.notificationsGraph

/**
 * Created by Ronak Harkhani on 22/04/23
 */

@Composable
fun NPinnerApp() {
    // TODO: Notification Permission Check and Blocker

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Route.NOTIFICATIONS
    ) {

        notificationsGraph(navController)

        notificationEditorGraph(navController)

    }
}


