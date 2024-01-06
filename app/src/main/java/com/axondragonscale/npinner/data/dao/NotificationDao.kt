package com.axondragonscale.npinner.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.axondragonscale.npinner.data.entity.NotificationEntity
import com.axondragonscale.npinner.model.NPinnerNotification
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ronak Harkhani on 30/04/23
 */

@Dao
interface NotificationDao {

    @Upsert
    suspend fun upsert(notifications: NotificationEntity)

    @Query(
        """
        SELECT *
        FROM notifications
        WHERE deletedAt IS NULL
        ORDER BY isPinned DESC, updatedAt DESC
        """
    )
    fun getNotifications(): Flow<List<NotificationEntity>>

    @Query(
        """
        SELECT *
        FROM notifications
        WHERE deletedAt IS NULL AND isPinned = 1
        ORDER BY updatedAt DESC
        """
    )
    fun getPinnedNotifications(): Flow<List<NotificationEntity>>

    @Query(
        """
        SELECT *
        FROM notifications
        WHERE id = :id
        LIMIT 1
        """
    )
    suspend fun getNotification(id: String): NotificationEntity

}
