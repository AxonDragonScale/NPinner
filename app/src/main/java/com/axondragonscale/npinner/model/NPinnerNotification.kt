package com.axondragonscale.npinner.model

/**
 * Created by Ronak Harkhani on 30/04/23
 */
data class NPinnerNotification(
    val id: String,
    val title: String,
    val content: String? = null,
    val isPinned: Boolean = true,
    val schedule: Schedule? = null,
    val createdAt: Long,
    val updatedAt: Long,
    val deletedAt: Long? = null,
)
