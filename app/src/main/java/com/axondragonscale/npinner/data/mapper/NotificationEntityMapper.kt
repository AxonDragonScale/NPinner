package com.axondragonscale.npinner.data.mapper

import com.axondragonscale.npinner.data.entity.NotificationEntity
import com.axondragonscale.npinner.model.NPinnerNotification

/**
 * Created by Ronak Harkhani on 30/04/23
 */
fun NPinnerNotification.toEntity() = toEntity(this)
fun List<NPinnerNotification>.toEntity() = this.map { it.toEntity() }
private fun toEntity(model: NPinnerNotification) = NotificationEntity(
    id = model.id,
    title = model.title,
    content = model.content,
    isPinned = model.isPinned,
    schedule = model.schedule,
    createdAt = model.createdAt,
    updatedAt = model.updatedAt,
    deletedAt = model.deletedAt,
)

fun NotificationEntity.toModel() = toModel(this)
fun List<NotificationEntity>.toModel() = this.map { it.toModel() }
private fun toModel(entity: NotificationEntity) = NPinnerNotification(
    id = entity.id,
    title = entity.title,
    content = entity.content,
    isPinned = entity.isPinned,
    schedule = entity.schedule,
    createdAt = entity.createdAt,
    updatedAt = entity.updatedAt,
    deletedAt = entity.deletedAt
)
