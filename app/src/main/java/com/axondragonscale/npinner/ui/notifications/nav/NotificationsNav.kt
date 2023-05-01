package com.axondragonscale.npinner.ui.notifications.nav

import androidx.navigation.NavController
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.axondragonscale.npinner.ui.Destination
import com.axondragonscale.npinner.ui.notifications.Notifications

/**
 * Created by Ronak Harkhani on 01/05/23
 */

fun NavGraphBuilder.notificationsGraph(navController: NavController) {
    composable(
        route = Destination.Notifications.setupRoute,
        deepLinks = listOf(NavDeepLink(Destination.Notifications.deepLink))
    ) {
        Notifications(navController = navController)
    }
}

fun NavController.navigateToNotifications() {
    navigate(route = Destination.Notifications.route)
}
