package com.axondragonscale.npinner.ui.screen.notificationEditor

import android.content.res.Configuration
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.axondragonscale.npinner.ui.common.PickerButton
import com.axondragonscale.npinner.ui.common.SegmentedToggleButton
import com.axondragonscale.npinner.ui.theme.NPinnerTheme

/**
 * Created by Ronak Harkhani on 29/04/23
 */

@Composable
fun Scheduler(
    scheduled: Boolean = false,
    modifier: Modifier = Modifier,
) {
    var scheduled by remember { mutableStateOf(scheduled) }
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (!scheduled) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Schedule",
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            } else {
                DateTimePicker()
            }

            IconToggleButton(
                modifier = Modifier.padding(12.dp),
                checked = scheduled,
                onCheckedChange = { scheduled = it }
            ) {
                Icon(
                    imageVector = if (scheduled) Icons.Outlined.Delete else Icons.Filled.Add,
                    contentDescription = if (scheduled) "Delete Schedule" else "Add Schedule",
                    tint = MaterialTheme.colorScheme.primary,
                )
            }
        }

        if (scheduled) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                var checked by remember { mutableStateOf(true) }
                Checkbox(
                    checked = checked,
                    onCheckedChange = { checked = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = MaterialTheme.colorScheme.primary,
                        uncheckedColor = MaterialTheme.colorScheme.onBackground
                    )
                )

                Text(
                    text = "REPEAT EVERY...",
                    style = MaterialTheme.typography.labelLarge,
                    color = if (checked) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            SegmentedToggleButton(
                modifier = Modifier.padding(horizontal = 24.dp).padding(bottom = 24.dp),
                items = listOf("DAY", "WEEK", "MONTH"),
                onItemSelected = {}
            )
        }

        Divider()
    }
}

@Composable
fun DateTimePicker(
    modifier: Modifier = Modifier,
) {
    Row {
        PickerButton(
            text = "Apr 28",
            onClick = { /*TODO*/ }
        )

        Spacer(modifier = Modifier.width(8.dp))

        PickerButton(
            text = "2:15 am",
            onClick = { /*TODO*/ }
        )
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SchedulerPreview() {
    NPinnerTheme {
        Surface {
            Scheduler(scheduled = true)
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SchedulerFalsePreview() {
    NPinnerTheme {
        Surface {
            Scheduler(scheduled = false)
        }
    }
}
