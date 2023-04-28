package com.axondragonscale.npinner.ui.screen.notificationEditor

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.axondragonscale.npinner.ui.common.PickerButton
import com.axondragonscale.npinner.ui.theme.NPinnerTheme

/**
 * Created by Ronak Harkhani on 29/04/23
 */

@Composable
fun Scheduler(
    modifier: Modifier = Modifier,
) {
    val scheduled = true
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max),
        contentAlignment = Alignment.CenterStart
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            if (!scheduled) {
                Text(
                    text = "Schedule",
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            } else {
                Spacer(modifier = Modifier.height(12.dp))
                DateTimePicker()
                Spacer(modifier = Modifier.height(24.dp))
            }
        }

        IconToggleButton(
            modifier = Modifier
                .padding(12.dp)
                .align(Alignment.TopEnd),
            checked = scheduled,
            onCheckedChange = { /*TODO*/ }
        ) {
            Icon(
                imageVector = if (scheduled) Icons.Outlined.Delete else Icons.Filled.Add,
                contentDescription = if (scheduled) "Delete Schedule" else "Add Schedule",
                tint = MaterialTheme.colorScheme.primary,
            )
        }

        Divider(modifier = Modifier.align(Alignment.BottomCenter))
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
            Scheduler()
        }
    }
}
