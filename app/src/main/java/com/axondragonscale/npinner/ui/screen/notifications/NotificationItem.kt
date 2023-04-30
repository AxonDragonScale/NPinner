package com.axondragonscale.npinner.ui.screen.notifications

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.axondragonscale.npinner.R
import com.axondragonscale.npinner.model.NPinnerNotification
import com.axondragonscale.npinner.model.Schedule
import com.axondragonscale.npinner.model.ScheduleType
import com.axondragonscale.npinner.ui.common.Divider
import com.axondragonscale.npinner.ui.common.IconLabel
import com.axondragonscale.npinner.ui.theme.NPinnerTheme
import com.axondragonscale.npinner.util.formatted
import com.axondragonscale.npinner.util.relativeTimeSpan
import java.time.LocalDate
import java.time.LocalTime

/**
 * Created by Ronak Harkhani on 27/04/23
 */

@Composable
fun NotificationItem(
    notification: NPinnerNotification,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background) // TODO: Change based on pinned or not
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            NotificationItemHeader(relativeTimeSpan = notification.updatedAt.relativeTimeSpan)

            Text(
                modifier = Modifier.padding(vertical = 8.dp),
                text = notification.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground // TODO: Change with background
            )

            notification.description.takeIf { !it.isNullOrBlank() }?.let { content ->
                Text(
                    text = content,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8F) // TODO: Change with background
                )
            }

            notification.schedule?.let {
                ScheduleButton(
                    modifier = Modifier.padding(top = 16.dp),
                    schedule = notification.schedule,
                    onClick = { /*TODO*/ },
                )
            }
        }

        IconToggleButton(
            modifier = Modifier
                .size(48.dp)
                .align(Alignment.TopEnd),
            checked = false,
            onCheckedChange = {}, // TODO: Pin and Unpin on click
            colors = IconButtonDefaults.iconToggleButtonColors(
                contentColor = MaterialTheme.colorScheme.primary
            ),
        ) {
            if (notification.isPinned) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_pin),
                    contentDescription = "Pin Notification",
                )
            } else {
                Icon(
                    painter = painterResource(id = R.drawable.ic_pinned),
                    contentDescription = "Unpin Notification",
                )
            }
        }

        Divider(modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
fun NotificationItemHeader(
    relativeTimeSpan: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconLabel(
            iconPainter = painterResource(id = R.drawable.ic_app_icon),
            labelText = "NPINNER",
            tint = MaterialTheme.colorScheme.primary
        )

        Text(
            modifier = Modifier.padding(horizontal = 8.dp),
            text = "•",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = relativeTimeSpan,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
fun ScheduleButton(
    schedule: Schedule,
    onClick: () -> Unit,
    tint: Color = MaterialTheme.colorScheme.primary,
    disabledTint: Color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2F),
    modifier: Modifier = Modifier,
) {
    val enabled by remember { mutableStateOf(true) } // TODO: Disable if scheduled time is in the past
    OutlinedButton(
        modifier = modifier.height(32.dp),
        onClick = onClick,
        shape = RoundedCornerShape(2.dp),
        contentPadding = PaddingValues(start = 8.dp, end = 12.dp),
        border = BorderStroke(width = 1.5.dp, color = if (enabled) tint else disabledTint),
        enabled = false,
    ) {
        Icon(
            modifier = Modifier.size(18.dp),
            imageVector = Icons.Outlined.CalendarToday,
            contentDescription = "Schedule",
            tint = if (enabled) tint else disabledTint
        )

        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = "${schedule.date.formatted} - ${schedule.time.formatted}",
            style = MaterialTheme.typography.labelSmall,
            color = if (enabled) tint else disabledTint,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun NotificationItemPreview() {
    NPinnerTheme {
        NotificationItem(
            notification = NPinnerNotification(
                id = "",
                title = "Title",
                description = stringResource(id = R.string.lorem_ipsum),
                isPinned = true,
                schedule = Schedule(LocalDate.now(), LocalTime.now(), ScheduleType.DAY),
                createdAt = 1L,
                updatedAt = 1L,
            )
        )
    }
}
