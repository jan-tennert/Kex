package io.github.jan.kex.ui.screen.exam.importer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import io.github.jan.kex.data.remote.ExamData
import io.github.jan.kex.data.remote.ExamPlan
import io.github.jan.kex.ui.nav.NavigationTarget
import io.github.jan.kex.vm.ExamPlanViewModel
import io.github.jan.kex.vm.ExamViewModel

@Composable
fun ExamImporterScreen(
    examPlanVm: ExamPlanViewModel,
    examVm: ExamViewModel,
    navigator: NavController
) {
    val planKeys by examPlanVm.examPlanKeys.collectAsStateWithLifecycle()
    val planKeysLoading by examPlanVm.examPlanKeysLoading.collectAsStateWithLifecycle()
    val examPlanLoading by examPlanVm.examPlanLoading.collectAsStateWithLifecycle()
    val examPlan by examPlanVm.examPlan.collectAsState()

    LaunchedEffect(Unit) {
        examPlanVm.updateExamPlansKeys()
    }

    when {
        examPlanLoading -> {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(Modifier.size(24.dp))
            }
        }
        examPlan == null -> {
            ExamPlanKeySelection(
                loading = planKeysLoading,
                keys = planKeys,
                refresh = {
                    examPlanVm.updateExamPlansKeys()
                },
                onSubmit = {
                    examPlanVm.updateExamPlan(it)
                }
            )
        }
        else -> {
            ExamImportSelector(
                plan = examPlan!!,
                onImport = { selectedSubjects ->
                    val exams = examPlan!!.examsByDate.toList().map { (date, exams) ->
                        exams.map {
                            ExamData(it.subject, date)
                        }
                    }.flatten().filter { it.subject in selectedSubjects }
                    examVm.importExams(exams + findMatchingAbiDates(examPlan!!, exams))
                    navigator.navigate(NavigationTarget.Exams.destination)
                },
                onBack = {
                    examPlanVm.examPlan.value = null
                }
            )
        }
    }
}

private fun findMatchingAbiDates(plan: ExamPlan, exams: List<ExamData>): List<ExamData> {
    val subjects = exams.filter { !it.subject.endsWith("B") }.map {
        it.subject.clean()
    }
    return plan.examsByDate.toList().map { (date, exams) ->
        exams.filter { e -> e.isAbi && subjects.any { it == e.subject.clean() || e.subject == "*" } }.map {
            ExamData(it.teacher + " " + it.subject, date)
        }
    }.filter { it.isNotEmpty() }.flatten()
}

private fun String.clean(): String {
    return if(this.last().isDigit()) this.substring(0, this.length - 1) else this
}
