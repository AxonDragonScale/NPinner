package com.axondragonscale.npinner.ui.screen.notificationEditor

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.axondragonscale.npinner.ui.Route
import com.axondragonscale.npinner.ui.common.BottomBar
import com.axondragonscale.npinner.ui.common.BottomButton
import com.axondragonscale.npinner.ui.common.Divider
import com.axondragonscale.npinner.ui.common.IconActionButton
import com.axondragonscale.npinner.ui.common.TopBar
import com.axondragonscale.npinner.ui.theme.Dimen
import com.axondragonscale.npinner.ui.theme.NPinnerTheme

/**
 * Created by Ronak Harkhani on 22/04/23
 */

@Composable
fun NotificationEditor(
    navController: NavController,
    notificationId: String?,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        TopBar(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .zIndex(1F),
            title = "Edit" // TODO: Or Create
        )

        Column(
            modifier = Modifier.padding(
                top = Dimen.TOP_BAR_HEIGHT,
                bottom = Dimen.BOTTOM_BAR_HEIGHT
            )
        ) {
            Editor()
            Divider()
            Scheduler()
        }

        BottomBar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .zIndex(1F),
            mainAction = {
                BottomButton(
                    modifier = Modifier.weight(1f),
                    title = "SAVE", // TODO: Or SAVE AND PIN
                    onClick = { /*TODO*/ }
                )
            },
            leftAction = {
                IconActionButton(
                    icon = Icons.Outlined.ArrowBack,
                    onClick = { navController.popBackStack() },
                )
            },
            rightAction = {
                if (false) { // TODO: Show Delete in case of edit
                    IconActionButton(
                        icon = Icons.Outlined.Delete,
                        onClick = { /*TODO*/ }
                    )
                } else {
                    Spacer(modifier = Modifier.width(48.dp))
                }
            }
        )

    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun NotificationEditorPreview() {
    NPinnerTheme {
        Surface {
            NotificationEditor(
                navController = rememberNavController(),
                notificationId = null,
            )
        }
    }
}
