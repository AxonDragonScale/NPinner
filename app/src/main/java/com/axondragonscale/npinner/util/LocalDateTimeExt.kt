package com.axondragonscale.npinner.util

import android.text.format.DateUtils
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

/**
 * Created by Ronak Harkhani on 01/05/23
 */

private const val DATE_FORMAT = "MMM d"
private val dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT)
val LocalDate.formatted: String
    get() = this.format(dateFormatter).uppercase()


private const val TIME_FORMAT = "h:mm a"
private val timeFormatter = DateTimeFormatter.ofPattern(TIME_FORMAT)
val LocalTime.formatted: String
    get() = this.format(timeFormatter).uppercase()

val Long.relativeTimeSpan: String
    get() = DateUtils.getRelativeTimeSpanString(
        this,
        System.currentTimeMillis(),
        DateUtils.SECOND_IN_MILLIS,
        DateUtils.FORMAT_ABBREV_RELATIVE
    ).toString()
