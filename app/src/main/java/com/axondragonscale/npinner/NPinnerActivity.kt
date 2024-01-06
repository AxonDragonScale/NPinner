package com.axondragonscale.npinner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.WorkManager
import com.axondragonscale.npinner.core.NPinnerNotificationMonitor
import com.axondragonscale.npinner.model.DarkModeConfig
import com.axondragonscale.npinner.repository.AppPrefsRepository
import com.axondragonscale.npinner.ui.NPinnerApp
import com.axondragonscale.npinner.ui.theme.NPinnerTheme
import com.axondragonscale.npinner.work.monitor.MonitorRequest
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NPinnerActivity : ComponentActivity() {

    @Inject lateinit var notificationMonitor: NPinnerNotificationMonitor
    @Inject lateinit var appPrefsRepository: AppPrefsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val darkModeConfig by appPrefsRepository.darkModeConfigFlow
                .collectAsStateWithLifecycle(initialValue = DarkModeConfig.AUTO)
            NPinnerTheme(darkTheme = isDarkMode(darkModeConfig = darkModeConfig)) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    NPinnerApp()
                }
            }
        }

        GlobalScope.launch {
            notificationMonitor.ensurePinnedNotificationVisibility()
            WorkManager.getInstance(this@NPinnerActivity)
                .enqueueUniquePeriodicWork(
                    MonitorRequest.MONITOR_TAG,
                    ExistingPeriodicWorkPolicy.KEEP,
                    MonitorRequest.getRequest(),
                )
        }
    }
    
}

@Composable
fun isDarkMode(darkModeConfig: DarkModeConfig) = when (darkModeConfig) {
    DarkModeConfig.AUTO -> isSystemInDarkTheme()
    DarkModeConfig.ON -> true
    DarkModeConfig.OFF -> false
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NPinnerTheme {
        NPinnerApp()
    }
}
