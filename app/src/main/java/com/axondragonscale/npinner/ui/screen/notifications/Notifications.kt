package com.axondragonscale.npinner.ui.screen.notifications

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.axondragonscale.npinner.ui.common.BottomBar
import com.axondragonscale.npinner.ui.common.BottomButton
import com.axondragonscale.npinner.ui.common.IconActionButton
import com.axondragonscale.npinner.ui.common.TopBar
import com.axondragonscale.npinner.ui.theme.NPinnerTheme

/**
 * Created by Ronak Harkhani on 22/04/23
 */
@Composable
fun Notifications() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(
            title = "Notifications"
        )

        BottomBar(
            mainAction = {
                BottomButton(
                    modifier = Modifier.weight(1f).padding(horizontal = 8.dp),
                    title = "MAIN ACTION",
                    onClick = { /*TODO*/ }
                )
            },
            leftAction = {
                IconActionButton(
                    modifier = Modifier.padding(start = 8.dp),
                    icon = Icons.Outlined.DarkMode,
                    onClick = { /*TODO*/ },
                )
            },
            rightAction = {
                IconActionButton(
                    modifier = Modifier.padding(end = 8.dp),
                    icon = Icons.Outlined.Info,
                    onClick = { /*TODO*/ }
                )
            }
        )
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TopBarPreview() {
    NPinnerTheme {
        Notifications()
    }
}



