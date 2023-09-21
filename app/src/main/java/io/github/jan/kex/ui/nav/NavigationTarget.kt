package io.github.jan.kex.ui.nav

import androidx.compose.ui.graphics.vector.ImageVector
import io.github.jan.kex.R
import io.github.jan.kex.ui.icons.ChecklistIcon
import io.github.jan.kex.ui.icons.ExamIcon
import io.github.jan.kex.ui.icons.HomeIcon
import io.github.jan.kex.ui.icons.SettingsIcon

sealed class NavigationTarget(val destination: String, val labelId: Int, val destinationFormat: String = destination) {

    data object Home: Main("home", HomeIcon, R.string.home)
    data object Subjects: Main("subjects", ChecklistIcon, R.string.tasks) {

        data object Detail: NavigationTarget("subjects/{subjectId}", R.string.tasks, "subjects/%s")

    }
    data object Exams: Main("exams", ExamIcon, R.string.exams) {
        data object Detail: NavigationTarget("exams/{examId}", R.string.exam_detail, "exams/%s")
        data object Create: NavigationTarget("exams/create", R.string.exam_create)
        data object Edit: NavigationTarget("exams/edit/{examId}", R.string.exam_edit, "exams/edit/%s")
        data object Import: NavigationTarget("exams/import", R.string.exam_import_screen)

        val entries = listOf(Detail, Create, Edit, Import)
    }
    data object Settings: Main("settings", SettingsIcon, R.string.settings)

    sealed class Main(destination: String, val icon: ImageVector, labelId: Int): NavigationTarget(destination, labelId) {

        companion object {
            val entries = listOf(Home, Subjects, Exams, Settings)
        }

    }

    companion object {
        val entries = Main.entries + Exams.entries
    }

}
