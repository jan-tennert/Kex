package io.github.jan.kex.ui.screen.exam.importer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.github.jan.kex.R
import io.github.jan.kex.data.remote.ExamPlan
import io.github.jan.kex.ui.components.ExamPlanCard
import io.github.jan.kex.ui.icons.rememberArrowBackIcon

@Composable
fun ExamImportSelector(plan: ExamPlan, onImport: (List<String>) -> Unit, onBack: () -> Unit) {
    val subjects = remember(plan) {
        plan.examsByDate.toList().map { (_, exams) ->
            exams.map { it }
        }.flatten().distinctBy { it.subject }.sortedBy { it.subject }
    }
    val selectedCourses = remember { mutableStateListOf<String>() }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(stringResource(R.string.select_courses), style = MaterialTheme.typography.headlineLarge)
        Text("B = Basisfächer")
        LazyVerticalGrid(
            columns = GridCells.Adaptive(128.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(subjects, { it.hashCode() }) {
                ExamPlanCard(selected = it.subject in selectedCourses, examPlanEntry = it) {
                    if (it.subject in selectedCourses) {
                        selectedCourses.remove(it.subject)
                    } else {
                        selectedCourses.add(it.subject)
                    }
                }
            }
        }
        Button(onClick = { onImport(selectedCourses) }, modifier = Modifier.padding(8.dp)) {
            Text(stringResource(id = R.string.importA))
        }
    }
    Box(contentAlignment = Alignment.BottomStart, modifier = Modifier.fillMaxSize().padding(8.dp)) {
        IconButton(onClick = onBack) {
            Icon(rememberArrowBackIcon(), contentDescription = null)
        }
    }
}