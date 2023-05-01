package com.axondragonscale.npinner.ui.notifications

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.axondragonscale.npinner.ui.common.BottomBar
import com.axondragonscale.npinner.ui.common.BottomButton
import com.axondragonscale.npinner.ui.common.IconActionButton
import com.axondragonscale.npinner.ui.common.TopBar
import com.axondragonscale.npinner.ui.notificationEditor.nav.navigateToNotificationEditor
import com.axondragonscale.npinner.ui.theme.Dimen
import com.axondragonscale.npinner.ui.theme.NPinnerTheme

/**
 * Created by Ronak Harkhani on 22/04/23
 */

@Composable
fun Notifications(
    navController: NavController,
    viewModel: NotificationsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Notifications(
        uiState = uiState,
        onCreateClick = { navController.navigateToNotificationEditor() },
        onNotificationClick = { navController.navigateToNotificationEditor(it) },
        onPinClick = viewModel::onPinClick
    )
}

@Composable
fun Notifications(
    uiState: NotificationsUiState,
    onCreateClick: () -> Unit,
    onNotificationClick: (String) -> Unit,
    onPinClick: (String, Boolean) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        TopBar(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .zIndex(1F),
            title = "Notifications"
        )

        if (uiState is NotificationsUiState.Success) {
            LazyColumn(
                modifier = Modifier.padding(
                    top = Dimen.TOP_BAR_HEIGHT,
                    bottom = Dimen.BOTTOM_BAR_HEIGHT
                )
            ) {
                items(uiState.notifications) { notification ->
                    NotificationItem(
                        modifier = Modifier.clickable {
                            onNotificationClick(notification.id)
                        },
                        notification = notification,
                        onPinClick = {
                            println("zeref - onPinClick $it")
                            onPinClick(notification.id, it)
                                     },
                    )
                }
            }
        }

        BottomBar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .zIndex(1F),
            mainAction = {
                BottomButton(
                    modifier = Modifier.weight(1f),
                    title = "CREATE",
                    onClick = onCreateClick
                )
            },
            leftAction = {
                IconActionButton(
                    icon = Icons.Outlined.DarkMode,
                    onClick = { /*TODO*/ },
                )
            },
            rightAction = {
                IconActionButton(
                    icon = Icons.Outlined.Info,
                    onClick = { /*TODO*/ }
                )
            }
        )
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun NotificationsPreview() {
    NPinnerTheme {
        Notifications(rememberNavController())
    }
}



