package com.axondragonscale.npinner.ui.notificationEditor

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.res.Configuration
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.axondragonscale.npinner.model.Schedule
import com.axondragonscale.npinner.model.ScheduleType
import com.axondragonscale.npinner.ui.common.PickerButton
import com.axondragonscale.npinner.ui.common.SegmentedToggleButton
import com.axondragonscale.npinner.ui.theme.NPinnerTheme
import com.axondragonscale.npinner.util.formatted
import java.time.LocalDate
import java.time.LocalTime

/**
 * Created by Ronak Harkhani on 29/04/23
 */

@Composable
fun Scheduler(
    schedule: Schedule?,
    onScheduleChange: (Schedule?) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (schedule != null) {
                DateTimePicker(
                    schedule = schedule,
                    onScheduleChange = onScheduleChange,
                )
            } else {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Schedule",
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            IconToggleButton(
                modifier = Modifier.padding(12.dp),
                checked = schedule != null,
                onCheckedChange = {
                    onScheduleChange(if (it) Schedule.newInstance() else null)
                }
            ) {
                Icon(
                    imageVector = if (schedule != null) Icons.Outlined.Delete else Icons.Filled.Add,
                    contentDescription = if (schedule != null) "Delete Schedule" else "Add Schedule",
                    tint = MaterialTheme.colorScheme.primary,
                )
            }
        }

        if (schedule != null) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Checkbox(
                    checked = schedule.type != null,
                    onCheckedChange = {
                        onScheduleChange(schedule.copy(type = if (it) ScheduleType.DAY else null))
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = MaterialTheme.colorScheme.primary,
                        uncheckedColor = MaterialTheme.colorScheme.onBackground
                    )
                )

                Text(
                    text = "REPEAT EVERY...",
                    style = MaterialTheme.typography.labelLarge,
                    color = if (schedule.type != null) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            SegmentedToggleButton(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .padding(bottom = 24.dp),
                items = listOf("DAY", "WEEK", "MONTH"),
                onItemSelected = {
                    onScheduleChange(schedule.copy(type = ScheduleType.fromOrdinal(it)))
                },
                enabled = schedule.type != null
            )
        }

        Divider()
    }
}

@Composable
fun DateTimePicker(
    schedule: Schedule,
    onScheduleChange: (Schedule?) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row {
        val context = LocalContext.current

        // TODO: PickerDialog Styling

        PickerButton(
            text = schedule.date.formatted,
            onClick = {
                DatePickerDialog(
                    context,
                    { _: DatePicker, year: Int, month: Int, day: Int ->
                        val newDate = LocalDate.of(year, month, day)
                        onScheduleChange(schedule.copy(date = newDate))
                    },
                    schedule.date.year,
                    schedule.date.monthValue,
                    schedule.date.dayOfMonth
                ).show()
            }
        )

        Spacer(modifier = Modifier.width(8.dp))

        PickerButton(
            text = schedule.time.formatted,
            onClick = {
                TimePickerDialog(
                    context,
                    { _: TimePicker, hour: Int, min: Int ->
                        val newTime = LocalTime.of(hour, min)
                        onScheduleChange(schedule.copy(time = newTime))
                    },
                    schedule.time.hour,
                    schedule.time.minute,
                    false
                ).show()
            }
        )
    }
}


@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SchedulerNullPreview() {
    NPinnerTheme {
        Surface {
            Scheduler(
                schedule = null,
                onScheduleChange = {},
            )
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SchedulerPreview() {
    NPinnerTheme {
        Surface {
            Scheduler(
                schedule = Schedule(
                    date = LocalDate.now(),
                    time = LocalTime.now(),
                    type = ScheduleType.DAY,
                ),
                onScheduleChange = {},
            )
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SchedulerNoRepeatPreview() {
    NPinnerTheme {
        Surface {
            Scheduler(
                schedule = Schedule(
                    date = LocalDate.now(),
                    time = LocalTime.now(),
                    type = null,
                ),
                onScheduleChange = {},
            )
        }
    }
}
