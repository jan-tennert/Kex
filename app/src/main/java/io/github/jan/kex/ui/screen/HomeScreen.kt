package io.github.jan.kex.ui.screen

import android.app.Activity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import io.github.jan.kex.data.remote.Exam
import io.github.jan.kex.data.remote.Task
import io.github.jan.kex.localizedDay
import io.github.jan.kex.ui.components.ExamHomeCard
import io.github.jan.kex.ui.components.TaskCard
import io.github.jan.kex.ui.components.daysUntil
import io.github.jan.kex.ui.nav.NavigationTarget
import io.github.jan.kex.vm.ExamViewModel
import io.github.jan.kex.vm.SubjectViewModel
import io.github.jan.kex.vm.TaskViewModel
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun HomeScreen(
    examVm: ExamViewModel = getViewModel(),
    taskVm: TaskViewModel = getViewModel(),
    subjectVm: SubjectViewModel = getViewModel(),
    navController: NavController
) {
    val exams by examVm.exams.collectAsStateWithLifecycle(emptyList())
    val examsByDays = remember(exams) { exams.groupBy { it.daysUntil }.entries.sortedBy { it.key }.filter { it.key >= 0 }.take(3) }
    val tasks by taskVm.tasks.collectAsStateWithLifecycle(emptyList())
    val subjects by subjectVm.subjects.collectAsStateWithLifecycle(emptyList())
    val tasksByDays = remember(tasks) {
        tasks.filter { it.doneDate == null }.groupBy { it.daysUntil }.entries.sortedBy { it.key }.filter { it.key >= 0 }.take(3)
    }
    val context = LocalContext.current
    val windowSizeClass = calculateWindowSizeClass(context as Activity)
    if(windowSizeClass.heightSizeClass == WindowHeightSizeClass.Medium) {
        Row {
            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.5f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                examList(examsByDays) {
                    navController.navigate(NavigationTarget.Exams.Detail.destinationFormat.format(it.id))
                }
            }
            Box(Modifier.padding(8.dp)) {
                VerticalDivider(Modifier.fillMaxHeight())
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                taskList(tasksByDays, taskVm::updateTask, taskVm::deleteTask)
            }
        }
    } else {
        Column {
            LazyColumn(Modifier.fillMaxHeight(0.5f).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                examList(examsByDays) {
                    navController.navigate(NavigationTarget.Exams.Detail.destinationFormat.format(it.id))
                }
            }
            Box(Modifier.padding(8.dp)) {
                HorizontalDivider(Modifier.fillMaxWidth())
            }
            LazyColumn(Modifier.fillMaxHeight().fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                taskList(tasksByDays, taskVm::updateTask, taskVm::deleteTask)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
private fun LazyListScope.examList(examsByDay: List<Map.Entry<Int, List<Exam>>>, onClick: (Exam) -> Unit) {
    item {
        Text("Anstehende Klassenarbeiten", fontSize = 25.sp)
    }
    examsByDay.forEach { (days, exams) ->
        item {
            Text(stringResource(days.localizedDay, days), fontSize = 20.sp)
        }
        items(exams) {
            ExamHomeCard(it, modifier = Modifier.padding(8.dp).animateItemPlacement()) { onClick(it) }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
private fun LazyListScope.taskList(tasksByDay: List<Map.Entry<Long, List<Task>>>, updateTask: (Task, String, Instant, Instant?) -> Unit, delete: (Long) -> Unit) {
    item {
        Text("Anstehende Hausaufgaben", fontSize = 25.sp)
    }
    tasksByDay.forEach { (days, tasks) ->
        item {
            Text(stringResource(days.toInt().localizedDay, days), fontSize = 20.sp)
        }
        items(tasks) {
            TaskCard(
                task = it,
                onUpdate = { done, task, dueDate ->
                    updateTask(
                        it,
                        task,
                        dueDate,
                        if (done) Clock.System.now() else null
                    )
                },
                onDelete = { delete(it.id) },
                modifier = Modifier.padding(8.dp).animateItemPlacement()
            )
        }
    }
}