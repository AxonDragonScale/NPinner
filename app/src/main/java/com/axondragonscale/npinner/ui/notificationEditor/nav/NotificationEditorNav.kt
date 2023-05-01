package com.axondragonscale.npinner.ui.notificationEditor.nav

import android.content.Intent
import androidx.navigation.NavController
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.axondragonscale.npinner.NPinnerConstants
import com.axondragonscale.npinner.ui.Route
import com.axondragonscale.npinner.ui.notificationEditor.NotificationEditor

/**
 * Created by Ronak Harkhani on 01/05/23
 */

fun NavGraphBuilder.notificationEditorGraph(navController: NavController) {
    composable(
        route = Route.NOTIFICATION_EDITOR + "?${Route.ARG_ID}={${Route.ARG_ID}}",
        deepLinks = listOf(
            navDeepLink {
                action = Intent.ACTION_EDIT
                uriPattern = NPinnerConstants.DeepLink.NOTIFICATION_EDITOR + "?${Route.ARG_ID}={${Route.ARG_ID}}"
            }
        ),
        arguments = listOf(navArgument(Route.ARG_ID) {
            type = NavType.StringType
            nullable = true
        })
    ) {
        NotificationEditor(navController = navController)
    }
}

fun NavController.navigateToNotificationEditor(id: String? = null) {
    if (id == null) navigate(Route.NOTIFICATION_EDITOR)
    else navigate("${Route.NOTIFICATION_EDITOR}?${Route.ARG_ID}=$id")
}
