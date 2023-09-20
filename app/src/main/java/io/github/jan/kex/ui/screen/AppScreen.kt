package io.github.jan.kex.ui.screen

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import io.github.jan.kex.ui.dialog.NotificationPermissionDialog
import io.github.jan.kex.ui.nav.NavigationTarget
import io.github.jan.kex.ui.screen.app.AppNavigationBar
import io.github.jan.kex.ui.screen.app.AppNavigationContent
import io.github.jan.kex.ui.screen.app.AppTopBar
import io.github.jan.kex.ui.screen.app.AutoRefresh
import io.github.jan.kex.vm.AuthenticationViewModel
import io.github.jan.kex.vm.ExamViewModel
import io.github.jan.kex.vm.SettingsViewModel
import io.github.jan.kex.vm.SubjectViewModel
import io.github.jan.kex.vm.TaskViewModel
import io.github.jan.kex.vm.UpdateViewModel
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class, ExperimentalPermissionsApi::class,
    ExperimentalFoundationApi::class
)
@Composable
fun AppScreen(
    examVm: ExamViewModel = getViewModel(),
    authVm: AuthenticationViewModel = getViewModel(),
    subjectVm: SubjectViewModel = getViewModel(),
    taskVm: TaskViewModel = getViewModel(),
    updateVm: UpdateViewModel = getViewModel(),
    settingsVm: SettingsViewModel = getViewModel()
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = remember(navBackStackEntry) { NavigationTarget.entries.firstOrNull { it.destination == navBackStackEntry?.destination?.route } }
    val showedNotificationDialog by settingsVm.showedNotificationDialog.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val windowSizeClass = calculateWindowSizeClass(context as Activity)
    AutoRefresh(
        examVm = examVm,
        authVm = authVm,
        subjectVm = subjectVm,
        taskVm = taskVm
    )
    Scaffold(
        topBar = {
            AppTopBar(currentDestination)
        },
        bottomBar = {
            if(windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact) {
                AppNavigationBar(
                    currentDestination = currentDestination?.destination,
                    navigate = { direction ->
                        navController.navigate(direction)
                    }
                )
            }
        }
    ) {
        AppNavigationContent(
            paddingValues = it,
            examVm = examVm,
            authVm = authVm,
            subjectVm = subjectVm,
            taskVm = taskVm,
            updateVm = updateVm,
            settingsVm = settingsVm,
            currentDestination = currentDestination,
            navController = navController,
            windowSizeClass = windowSizeClass
        )
    }
    if(!showedNotificationDialog) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            NotificationPermissionDialog {
                settingsVm.setShowedNotificationDialog(true)
            }
        }
    }
}

