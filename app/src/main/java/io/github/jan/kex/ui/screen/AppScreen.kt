package io.github.jan.kex.ui.screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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
import io.github.jan.kex.ui.nav.NavigationTarget
import io.github.jan.kex.ui.screen.exam.ExamCreateScreen
import io.github.jan.kex.ui.screen.exam.ExamDetailScreen
import io.github.jan.kex.ui.screen.exam.ExamEditScreen
import io.github.jan.kex.ui.screen.exam.ExamScreen
import io.github.jan.kex.ui.screen.subjects.SubjectDetailScreen
import io.github.jan.kex.ui.screen.subjects.SubjectScreen
import io.github.jan.kex.vm.AuthenticationViewModel
import io.github.jan.kex.vm.ExamViewModel
import io.github.jan.kex.vm.SubjectViewModel
import io.github.jan.kex.vm.TaskViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun AppScreen(
    examVm: ExamViewModel = getViewModel(),
    authVm: AuthenticationViewModel = getViewModel(),
    subjectVm: SubjectViewModel = getViewModel(),
    taskVm: TaskViewModel = getViewModel()
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = remember(navBackStackEntry) { NavigationTarget.entries.firstOrNull { it.destination == navBackStackEntry?.destination?.route } }
    AutoRefresh()
    Scaffold(
        topBar = {
            AppTopBar(currentDestination)
        }
    ) {
        Row(
            Modifier
                .fillMaxSize()
                .padding(it)) {
            AppNavigationRail(
                currentDestination = currentDestination?.destination,
                navigate = { direction ->
                    navController.navigate(direction)
                }
            )
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
                    SettingsScreen(authVm, examVm, subjectVm, taskVm)
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
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                examVm.refreshExams(authVm.schoolUsername.value, authVm.schoolPassword.value)
                subjectVm.refreshSubjects()
                taskVm.refreshTasks()
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}