package com.axondragonscale.npinner.ui.common

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.axondragonscale.npinner.ui.theme.NPinnerTheme

/**
 * Created by Ronak Harkhani on 26/04/23
 */

private val BOTTOM_BAR_HEIGHT = 64.dp

@Composable
fun BottomBar(
    mainAction: @Composable RowScope.() -> Unit,
    leftAction: @Composable RowScope.() -> Unit = {},
    rightAction: @Composable RowScope.() -> Unit = {},
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(BOTTOM_BAR_HEIGHT)
            .background(MaterialTheme.colorScheme.surface),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .align(Alignment.TopCenter)
                .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.15F))
        )

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            leftAction()
            mainAction()
            rightAction()
        }
    }
}

@Composable
fun BottomButton(
    title: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = RoundedCornerShape(2.dp),
    ) {
        Text(text = title)
    }
}

@Composable
fun IconActionButton(
    icon: ImageVector,
    onClick: () -> Unit,
    iconDescription: String? = null,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier.padding(8.dp),
    ) {
        Icon(
            imageVector = icon,
            contentDescription = iconDescription,
            tint = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun BottomBarPreview() {
    NPinnerTheme {
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
                    onClick = { /*TODO*/ })
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
