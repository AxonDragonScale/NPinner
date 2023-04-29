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
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Route.NOTIFICATIONS
    ) {

        composable(route = Route.NOTIFICATIONS) {
            Notifications(navController = navController)
        }

        composable(
            route = Route.NOTIFICATION_EDITOR + "?${Route.ARG_ID}={${Route.ARG_ID}}",
            arguments = listOf(navArgument("${Route.ARG_ID}") {
                type = NavType.StringType
                nullable = true
            })
        ) { navBackStackEntry ->
            val args = navBackStackEntry.arguments
            val notificationId = args?.getString(Route.ARG_ID)

            NotificationEditor(
                navController = navController,
                notificationId = notificationId,
            )
        }

    }
}


