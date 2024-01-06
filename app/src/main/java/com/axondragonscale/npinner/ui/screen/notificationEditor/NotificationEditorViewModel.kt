package com.axondragonscale.npinner.ui.screen.notificationEditor

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.axondragonscale.npinner.model.NPinnerNotification
import com.axondragonscale.npinner.model.Schedule
import com.axondragonscale.npinner.repository.NotificationRepository
import com.axondragonscale.npinner.ui.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

/**
 * Created by Ronak Harkhani on 30/04/23
 */

@HiltViewModel
class NotificationEditorViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: NotificationRepository,
) : ViewModel() {

    private val id = savedStateHandle.get<String>(Route.ARG_ID)
    private val createNew = id == null
    private val notificationFlow = MutableStateFlow(getNotification(id))

    val uiState: StateFlow<NotificationEditorUiState> =
        notificationEditorUiState().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = NotificationEditorUiState.Loading
        )

    fun onContentChange(title: String, desription: String) = viewModelScope.launch {
        notificationFlow.emit(notificationFlow.value.copy(title = title, description = desription))
    }

    fun onScheduleChange(schedule: Schedule?) = viewModelScope.launch {
        notificationFlow.emit(notificationFlow.value.copy(schedule = schedule))
    }

    fun onSaveClick() = viewModelScope.launch {
        repository.upsert(notificationFlow.value)
    }

    fun onDeleteClick() = viewModelScope.launch {
        repository.upsert(notificationFlow.value)
    }

    private fun notificationEditorUiState(): Flow<NotificationEditorUiState> =
        notificationFlow
            .map { NotificationEditorUiState.Success(createNew, it) }

    private fun getNotification(id: String?): NPinnerNotification {
        return if (id == null) NPinnerNotification.newInstance()
        else runBlocking { repository.getNotification(id) }
    }
}