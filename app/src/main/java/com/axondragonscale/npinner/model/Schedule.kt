package com.axondragonscale.npinner.model

import androidx.annotation.Keep
import java.time.LocalDate
import java.time.LocalTime

/**
 * Created by Ronak Harkhani on 30/04/23
 */

@Keep
data class Schedule(
    val date: LocalDate,
    val time: LocalTime,
    val type: ScheduleType,
)
