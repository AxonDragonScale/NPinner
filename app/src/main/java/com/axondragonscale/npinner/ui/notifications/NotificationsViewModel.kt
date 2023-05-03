package com.axondragonscale.npinner.ui.notifications

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.axondragonscale.npinner.core.NPinnerNotificationManager
import com.axondragonscale.npinner.core.NPinnerNotificationScheduler
import com.axondragonscale.npinner.model.NPinnerNotification
import com.axondragonscale.npinner.repository.NotificationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Ronak Harkhani on 30/04/23
 */

@HiltViewModel
class NotificationsViewModel @Inject constructor(
    private val repository: NotificationRepository,
    private val notificationManager: NPinnerNotificationManager,
    private val notificationScheduler: NPinnerNotificationScheduler,
) : ViewModel() {

    val uiState: StateFlow<NotificationsUiState> =
        notificationsUiState().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = NotificationsUiState.Loading
        )

    fun onPinClick(notificationId: String, isPinned: Boolean) = viewModelScope.launch {
        repository.updatePinStatus(notificationId, isPinned)
        if (isPinned) notificationManager.postNotification(repository.getNotification(notificationId))
        else notificationManager.dismissNotification(notificationId)
    }

    fun onNotificationDelete(notification: NPinnerNotification) = viewModelScope.launch {
        repository.delete(notification)
        notificationManager.dismissNotification(notification.id)
        notificationScheduler.cancelScheduledNotification(notification.id)
    }

    private fun notificationsUiState(): Flow<NotificationsUiState> =
        repository.getNotifications()
            .map { notifications ->
                NotificationsUiState.Success(notifications)
            }
}
