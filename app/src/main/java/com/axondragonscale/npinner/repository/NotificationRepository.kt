package com.axondragonscale.npinner.repository

import com.axondragonscale.npinner.data.dao.NotificationDao
import com.axondragonscale.npinner.data.mapper.toEntity
import com.axondragonscale.npinner.data.mapper.toModel
import com.axondragonscale.npinner.model.NPinnerNotification
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by Ronak Harkhani on 30/04/23
 */
class NotificationRepository @Inject constructor(
    private val notificationDao: NotificationDao,
) {

    suspend fun upsert(notification: NPinnerNotification) {
        val updatedNotification = notification.copy(updatedAt = System.currentTimeMillis())
        notificationDao.upsert(updatedNotification.toEntity())
    }

    fun getNotifications() =
        notificationDao.getNotifications().map { it.toModel() }

    suspend fun getNotification(id: String) =
        notificationDao.getNotification(id).toModel()

    suspend fun delete(notification: NPinnerNotification) {
        val deletedNotification = notification.copy(deletedAt = System.currentTimeMillis())
        notificationDao.upsert(deletedNotification.toEntity())
    }
}