package com.axondragonscale.npinner.repository

import com.axondragonscale.npinner.data.dao.NotificationDao
import com.axondragonscale.npinner.data.mapper.toModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by Ronak Harkhani on 30/04/23
 */
class NotificationRepository @Inject constructor(
    private val notificationDao: NotificationDao,
) {
    fun getNotifications() =
        notificationDao.getNotifications()
            .map { it.toModel() }
}
