package com.axondragonscale.npinner.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.axondragonscale.npinner.ui.screen.notificationEditor.NotificationEditor
import com.axondragonscale.npinner.ui.screen.notifications.Notifications

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
        // TODO: Improve Navigation. See NowInAndroid for reference.

        composable(route = Route.NOTIFICATIONS) {
            Notifications(navController = navController)
        }

        composable(
            route = Route.NOTIFICATION_EDITOR + "?${Route.ARG_ID}={${Route.ARG_ID}}",
            arguments = listOf(navArgument(Route.ARG_ID) {
                type = NavType.StringType
                nullable = true
            })
        ) {
            NotificationEditor(navController = navController)
        }

    }
}


