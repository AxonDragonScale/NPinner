package com.axondragonscale.npinner.ui.notifications.themePicker

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.axondragonscale.npinner.model.DarkModeConfig
import com.axondragonscale.npinner.ui.common.NPinnerModalBottomSheet
import com.axondragonscale.npinner.ui.common.SegmentedToggleButton
import com.axondragonscale.npinner.ui.theme.NPinnerTheme

/**
 * Created by Ronak Harkhani on 13/05/23
 */

@Composable
fun ThemePickerBottomSheet(
    onDismiss: () -> Unit,
    viewModel: ThemePickerViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    NPinnerModalBottomSheet(onDismiss = onDismiss) {
        ThemePicker(
            uiState = uiState,
            onDarkModeConfigChange = viewModel::onDarkModeConfigChange,
        )
    }
}

@Composable
private fun ThemePicker(
    uiState: ThemePickerUiState,
    onDarkModeConfigChange: (DarkModeConfig) -> Unit,
) {
    if (uiState is ThemePickerUiState.Success) {
        Column(modifier = Modifier.padding(24.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Outlined.DarkMode,
                    contentDescription = "Dark Mode",
                )
                
                Text(
                    modifier = Modifier.padding(start = 24.dp),
                    text = "Dark Mode",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            SegmentedToggleButton(
                items = DarkModeConfig.stringValues,
                defaultSelectedItemIndex = uiState.darkModeConfig.ordinal,
                onItemSelected = {
                    onDarkModeConfigChange(DarkModeConfig.fromOrdinal(it))
                },
            )
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ThemePickerBottomSheetPreview() {
    NPinnerTheme {
        Surface(color = MaterialTheme.colorScheme.surface) {
            ThemePicker(
                uiState = ThemePickerUiState.Success(DarkModeConfig.AUTO),
                onDarkModeConfigChange = {},
            )
        }
    }
}
