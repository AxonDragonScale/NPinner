package com.axondragonscale.npinner.model

/**
 * Created by Ronak Harkhani on 30/04/23
 */
enum class ScheduleType {
    DAY, WEEK, MONTH;

    companion object {
        private val values = values()

        fun fromOrdinal(ordinal: Int) = values[ordinal]
    }
}
