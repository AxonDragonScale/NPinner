package com.axondragonscale.npinner.ui.notifications.about

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.OpenInNew
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.axondragonscale.npinner.BuildConfig
import com.axondragonscale.npinner.NPinnerConstants
import com.axondragonscale.npinner.ui.common.NPinnerModalBottomSheet
import com.axondragonscale.npinner.ui.theme.NPinnerTheme

/**
 * Created by Ronak Harkhani on 13/05/23
 */

@Composable
fun AboutBottomSheet(onDismiss: () -> Unit) {
    NPinnerModalBottomSheet(onDismiss = onDismiss) {
        AboutContent()
    }
}

@Composable
fun AboutContent() {
    Column {
        AboutHeader()
        SourceCodeButton()
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun AboutHeader() {
    Column(modifier = Modifier.padding(24.dp)) {
        Text(
            text = "NPINNER",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
        )
        
        Text(
            modifier = Modifier.padding(top = 2.dp),
            text = "VERSION ${BuildConfig.VERSION_NAME}",
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Medium,
        )
    }
}

@Composable
fun SourceCodeButton() {
    val uriHandler = LocalUriHandler.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .clickable {
                uriHandler.openUri(NPinnerConstants.GITHUB_LINK)
            },
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier.padding(16.dp),
            imageVector = Icons.Outlined.OpenInNew,
            contentDescription = "Open Source Code",
        )
        
        Text(
            text = "Source Code",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
        )
    }
}


@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ThemePickerBottomSheetPreview() {
    NPinnerTheme {
        Surface(color = MaterialTheme.colorScheme.surface) {
            AboutContent()
        }
    }
}
