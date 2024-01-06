package com.axondragonscale.npinner.ui.notifications

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.axondragonscale.npinner.R
import com.axondragonscale.npinner.model.NPinnerNotification
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
        onPinClick = viewModel::onPinClick,
        onRemoveSchedule = viewModel::onRemoveSchedule,
        onNotificationDelete = viewModel::onNotificationDelete,
    )
}

@Composable
fun Notifications(
    uiState: NotificationsUiState,
    onCreateClick: () -> Unit,
    onNotificationClick: (String) -> Unit,
    onPinClick: (String, Boolean) -> Unit,
    onRemoveSchedule: (NPinnerNotification) -> Unit,
    onNotificationDelete: (NPinnerNotification) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        TopBar(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .zIndex(1F),
            title = "Notifications"
        )
        
        if (uiState is NotificationsUiState.Success) {
            if (uiState.notifications.isEmpty()) {
                NoNotifications()
            } else {
                LazyColumn(
                    modifier = Modifier.padding(
                        top = Dimen.TOP_BAR_HEIGHT,
                        bottom = Dimen.BOTTOM_BAR_HEIGHT
                    )
                ) {
                    items(uiState.notifications, key = { it.id }) { notification ->
                        SwipeableNotificationItem(
                            notification = notification,
                            onNotificationClick = onNotificationClick,
                            onPinClick = onPinClick,
                            onNotificationDelete = onNotificationDelete,
                            onRemoveSchedule = onRemoveSchedule,
                        )
                    }
                }
            }
        }
        
        var showThemeSelectionBottomSheet by remember { mutableStateOf(false) }
        var showAboutBottomSheet by remember { mutableStateOf(false) }
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
                    onClick = { showThemeSelectionBottomSheet = true },
                )
            },
            rightAction = {
                IconActionButton(
                    icon = Icons.Outlined.Info,
                    onClick = { showAboutBottomSheet = true }
                )
            }
        )
        
        if (showThemeSelectionBottomSheet)
            ThemeSelectionBottomSheet(onDismiss = { showThemeSelectionBottomSheet = false })
        
        if (showAboutBottomSheet)
            AboutBottomSheet(onDismiss = { showAboutBottomSheet = false })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeableNotificationItem(
    notification: NPinnerNotification,
    onNotificationClick: (String) -> Unit,
    onPinClick: (String, Boolean) -> Unit,
    onNotificationDelete: (NPinnerNotification) -> Unit,
    onRemoveSchedule: (NPinnerNotification) -> Unit,
    modifier: Modifier = Modifier,
) {
    val dismissState = rememberDismissState(
        confirmValueChange = {
            if (it != DismissValue.Default) onNotificationDelete(notification)
            true
        },
        positionalThreshold = { it * 0.5F },
    )
    
    SwipeToDismiss(
        modifier = modifier,
        state = dismissState,
        background = {
            val alignment = when (dismissState.dismissDirection) {
                DismissDirection.StartToEnd -> Alignment.CenterStart
                DismissDirection.EndToStart -> Alignment.CenterEnd
                else -> return@SwipeToDismiss
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.errorContainer),
            ) {
                Icon(
                    modifier = Modifier
                        .align(alignment)
                        .padding(24.dp)
                        .size(24.dp),
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = "Delete",
                    tint = MaterialTheme.colorScheme.onErrorContainer,
                )
            }
        },
        dismissContent = {
            NotificationItem(
                notification = notification,
                onClick = { onNotificationClick(notification.id) },
                onPinClick = { onPinClick(notification.id, it) },
                onRemoveSchedule = { onRemoveSchedule(notification) }
            )
        }
    )
}

@Composable
fun NoNotifications(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(80.dp),
            painter = painterResource(id = R.drawable.ic_app_icon),
            contentDescription = "NPinner",
            tint = MaterialTheme.colorScheme.primary
        )
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = "No notifications",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.SemiBold,
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



