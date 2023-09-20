package io.github.jan.kex.ui.screen.app

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import io.github.jan.kex.ui.nav.NavigationTarget
import io.github.jan.kex.ui.screen.HomeScreen
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

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class, ExperimentalAnimationApi::class)
@Composable
fun AppNavigationContent(
    paddingValues: PaddingValues,
    examVm: ExamViewModel,
    authVm: AuthenticationViewModel,
    subjectVm: SubjectViewModel,
    taskVm: TaskViewModel,
    updateVm: UpdateViewModel,
    settingsVm: SettingsViewModel,
    currentDestination: NavigationTarget?,
    navController: NavHostController,
    windowSizeClass: WindowSizeClass
) {
    Row(
        Modifier
            .fillMaxSize()
            .padding(paddingValues)) {
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
            startDestination = NavigationTarget.Home.destination,
        ) {
            animatedComposable(
                NavigationTarget.Home.destination,
            ) {
                HomeScreen(examVm, taskVm, subjectVm, navController)
            }
            animatedComposable(NavigationTarget.Exams.destination) {
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
                        onDelete = { examVm.deleteExam(selectedExam, selectedExam.custom); navController.navigate(
                            NavigationTarget.Exams.destination) }
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
            animatedComposable(NavigationTarget.Settings.destination) {
                SettingsScreen(authVm, examVm, subjectVm, taskVm, updateVm, settingsVm)
            }
            animatedComposable(NavigationTarget.Subjects.destination) {
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




