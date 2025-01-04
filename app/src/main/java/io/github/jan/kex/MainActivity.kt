package io.github.jan.kex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.jan.kex.ui.screen.RootScreen
import io.github.jan.kex.ui.theme.KexTheme
import io.github.jan.kex.vm.SettingsViewModel
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.handleDeeplinks
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : ComponentActivity() {

    private val supabaseClient by inject<SupabaseClient>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supabaseClient.handleDeeplinks(intent)
        setContent {
            val settingsVm: SettingsViewModel = getViewModel()
            val theme by settingsVm.currentTheme.collectAsStateWithLifecycle()
            KexTheme(theme) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RootScreen(settingsVm = settingsVm)
                }
            }
        }
    }
}