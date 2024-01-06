package com.axondragonscale.npinner.ui.notifications.nav

import androidx.navigation.NavController
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.axondragonscale.npinner.NPinnerConstants
import com.axondragonscale.npinner.ui.Route
import com.axondragonscale.npinner.ui.notifications.Notifications

/**
 * Created by Ronak Harkhani on 01/05/23
 */

fun NavGraphBuilder.notificationsGraph(navController: NavController) {
    composable(
        route = Route.NOTIFICATIONS,
        deepLinks = listOf(NavDeepLink(NPinnerConstants.DeepLink.NOTIFICTIONS))
    ) {
        Notifications(navController = navController)
    }
}

fun NavController.navigateToNotifications() {
    navigate(route = Route.NOTIFICATIONS)
}
