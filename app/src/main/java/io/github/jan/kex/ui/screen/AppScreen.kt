package io.github.jan.kex.ui.screen

import android.app.Activity
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import io.github.jan.kex.ui.dialog.NotificationPermissionDialog
import io.github.jan.kex.ui.nav.NavigationTarget
import io.github.jan.kex.ui.screen.exam.ExamCreateScreen
import io.github.jan.kex.ui.screen.exam.ExamDetailScreen
import io.github.jan.kex.ui.screen.exam.ExamEditScreen
import io.github.jan.kex.ui.screen.exam.ExamScreen
import io.github.jan.kex.ui.screen.settings.SettingsScreen
import io.github.jan.kex.ui.screen.subjects.SubjectDetailScreen
import io.github.jan.kex.ui.screen.subjects.SubjectScreen
import io.github.jan.kex.vm.AuthenticationViewModel
import io.github.jan.kex.vm.ExamViewModel
import io.github.jan.kex.vm.SettingsViewModel
import io.github.jan.kex.vm.SubjectViewModel
import io.github.jan.kex.vm.TaskViewModel
import io.github.jan.kex.vm.UpdateViewModel
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class, ExperimentalPermissionsApi::class)
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
    val context = LocalContext.current
    val windowSizeClass = calculateWindowSizeClass(context as Activity)
    val showedNotificationDialog by settingsVm.showedNotificationDialog.collectAsStateWithLifecycle()
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
        Row(
            Modifier
                .fillMaxSize()
                .padding(it)) {
            if(windowSizeClass.widthSizeClass != WindowWidthSizeClass.Compact) {
                AppNavigationRail(
                    currentDestination = currentDestination?.destination,
                    navigate = { direction ->
                        navController.navigate(direction)
                    }
                )
            }
            NavHost(
                navController = navController,
                startDestination = NavigationTarget.Home.destination
            ) {
                composable(NavigationTarget.Home.destination) {
                    HomeScreen(examVm, taskVm, subjectVm, navController)
                }
                composable(NavigationTarget.Exams.destination) {
                    ExamScreen(examVm, authVm, navController)
                }
                composable(NavigationTarget.Exams.Detail.destination, arguments = listOf(navArgument("examId") { type = NavType.StringType })) { entry ->
                    val exams by examVm.exams.collectAsStateWithLifecycle(emptyList())
                    val examId = entry.arguments?.getString("examId") ?: error("No examId provided")
                    val selectedExam = remember(exams) { exams.firstOrNull { exam -> exam.id == examId } }
                    selectedExam?.let {
                        ExamDetailScreen(
                            exam = selectedExam,
                            onEdit = { navController.navigate(NavigationTarget.Exams.Edit.destinationFormat.format(it.id)) },
                            onDelete = { examVm.deleteExam(selectedExam, selectedExam.custom); navController.navigate(NavigationTarget.Exams.destination) }
                        )
                    }
                }
                composable(NavigationTarget.Exams.Create.destination) {
                    val subjectSuggestions by examVm.subjectSuggestions.collectAsStateWithLifecycle()
                    ExamCreateScreen(subjectSuggestions) { subject, date, theme, type ->
                        examVm.createExam(subject, date, theme, type)
                        navController.navigate(NavigationTarget.Exams.destination)
                    }
                }
                composable(NavigationTarget.Exams.Edit.destination, arguments = listOf(navArgument("examId") { type = NavType.StringType})) { entry ->
                    val exams by examVm.exams.collectAsStateWithLifecycle(emptyList())
                    val examId = entry.arguments?.getString("examId") ?: error("No examId provided")
                    val selectedExam = remember(exams) { exams.firstOrNull { exam -> exam.id == examId } }
                    val subjectSuggestions by examVm.subjectSuggestions.collectAsStateWithLifecycle()
                    selectedExam?.let {  exam ->
                        ExamEditScreen(
                            exam = exam,
                            suggestions = subjectSuggestions,
                            onEdit = { subject, theme, points ->
                                examVm.updateExam(
                                    selectedExam,
                                    subject,
                                    theme,
                                    points?.toLongOrNull()
                                )
                                navController.popBackStack()
                            })
                    }
                }
                composable(NavigationTarget.Settings.destination) {
                    SettingsScreen(authVm, examVm, subjectVm, taskVm, updateVm, settingsVm)
                }
                composable(NavigationTarget.Subjects.destination) {
                    SubjectScreen(subjectVm, taskVm, navController)
                }
                composable(NavigationTarget.Subjects.Detail.destination, arguments = listOf(navArgument("subjectId") { type = NavType.StringType })) { entry ->
                    SubjectDetailScreen(
                        subjectId = entry.arguments?.getString("subjectId")?.toLongOrNull() ?: error("No subjectId provided"),
                        taskViewModel = taskVm,
                        subjectViewModel = subjectVm,
                        navController = navController
                    )
                }
            }
        }
    }
    if(!showedNotificationDialog) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            NotificationPermissionDialog {
                settingsVm.setShowedNotificationDialog(true)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(currentTarget: NavigationTarget?) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = currentTarget?.labelId?.let { stringResource(it) } ?: ""
            )
        }
    )
}

//For phones
@Composable
fun AppNavigationBar(
    currentDestination: String?,
    navigate: (String) -> Unit
) {
    NavigationBar {
        NavigationTarget.Main.entries.forEach {
            NavigationBarItem(
                selected = it.destination == currentDestination,
                onClick = { navigate(it.destination) },
                icon = {
                    Icon(
                        imageVector = it.icon,
                        contentDescription = null
                    )
                })
        }
    }
}

//For tablets
@Composable
fun AppNavigationRail(
    currentDestination: String?,
    navigate: (String) -> Unit
) {
    NavigationRail {
        NavigationTarget.Main.entries.forEach {
            NavigationRailItem(
                selected = currentDestination == it.destination,
                onClick = { navigate(it.destination) },
                icon = {
                    Icon(
                        imageVector = it.icon,
                        contentDescription = null
                    )
                })
        }
    }
}

@Composable
fun AutoRefresh(
    examVm: ExamViewModel = getViewModel(),
    authVm: AuthenticationViewModel = getViewModel(),
    subjectVm: SubjectViewModel = getViewModel(),
    taskVm: TaskViewModel = getViewModel()
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    DisposableEffect(lifecycleOwner, context) {
        val connectivityManager = context.getSystemService(ConnectivityManager::class.java)
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
             //   examVm.syncExams(authVm.schoolUsername.value, authVm.schoolPassword.value)
                subjectVm.refreshSubjects()

                taskVm.syncTasks()
            }
        }

        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                taskVm.syncTasks()
                examVm.syncExams(authVm.schoolUsername.value, authVm.schoolPassword.value)
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)
        connectivityManager?.registerDefaultNetworkCallback(networkCallback)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            connectivityManager?.unregisterNetworkCallback(networkCallback)
        }
    }
}