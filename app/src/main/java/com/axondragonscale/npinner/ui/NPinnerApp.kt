package com.axondragonscale.npinner.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.axondragonscale.npinner.ui.notificationEditor.nav.notificationEditorGraph
import com.axondragonscale.npinner.ui.notifications.nav.notificationsGraph

/**
 * Created by Ronak Harkhani on 22/04/23
 */

@Composable
fun NPinnerApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Destination.Notifications.route
    ) {

        notificationsGraph(navController)

        notificationEditorGraph(navController)

    }
}


