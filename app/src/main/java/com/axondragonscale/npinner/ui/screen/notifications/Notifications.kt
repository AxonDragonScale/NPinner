package com.axondragonscale.npinner.ui.screen.notifications

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
                    title = "MAIN ACTION",
                    onClick = { /*TODO*/ },
                    modifier = Modifier.weight(1f)
                )
            },
            leftAction = {
                IconActionButton(
                    icon = Icons.Outlined.DarkMode,
                    onClick = { /*TODO*/ }
                )
            },
            rightAction = {
                IconActionButton(
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



