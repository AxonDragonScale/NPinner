package com.axondragonscale.npinner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.axondragonscale.npinner.core.NPinnerNotificationMonitor
import com.axondragonscale.npinner.ui.NPinnerApp
import com.axondragonscale.npinner.ui.theme.NPinnerTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NPinnerActivity : ComponentActivity() {

    @Inject
    lateinit var notificationMonitor: NPinnerNotificationMonitor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NPinnerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NPinnerApp()
                }
            }
        }

        GlobalScope.launch {
            notificationMonitor.ensurePinnedNotificationVisibility()
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NPinnerTheme {
        NPinnerApp()
    }
}
