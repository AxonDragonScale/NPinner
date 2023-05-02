package com.axondragonscale.npinner.model

/**
 * Created by Ronak Harkhani on 30/04/23
 */
enum class ScheduleType {
    DAY, WEEK, MONTH;

    companion object {
        private val values by lazy { values() }

        val stringValues by lazy { values.map { it.name } }

        fun fromOrdinal(ordinal: Int) = values[ordinal]
    }
}
