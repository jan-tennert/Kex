package io.github.jan.kex.ui.screen.exam

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.jan.kex.R
import io.github.jan.kex.data.remote.Exam
import io.github.jan.kex.ui.dialog.DeleteDialog
import io.github.jan.kex.ui.dialog.exam.EditExamDialog
import io.github.jan.kex.ui.icons.EditIcon
import io.github.jan.kex.ui.icons.rememberDelete

@Composable
fun ExamDetailScreen(exam: Exam, onEdit: (subject: String, theme: String?, points: String?) -> Unit, onDelete: () -> Unit) {
    var showEditDialog by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.align(Alignment.Start)
        ) {
            Text(exam.subject, fontSize = 40.sp)
            Text("${exam.date.dayOfMonth}.${exam.date.monthNumber}.${exam.date.year}", fontSize = 15.sp)
            Spacer(modifier = Modifier.padding(8.dp))
            Text(stringResource(R.string.theme), fontSize = 25.sp, fontWeight = FontWeight.Bold)
            Text(exam.theme ?: stringResource(R.string.no_theme), fontSize = 20.sp)
            Spacer(modifier = Modifier.padding(14.dp))
            Text(stringResource(R.string.score), fontSize = 25.sp, fontWeight = FontWeight.Bold)
            Text(exam.points?.toString() ?: stringResource(R.string.no_score), fontSize = 20.sp)
        }
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp), contentAlignment = Alignment.BottomEnd
    ) {
        ExtendedFloatingActionButton(onClick = { showEditDialog = true }) {
            Icon(EditIcon, contentDescription = null)
            Spacer(modifier = Modifier.padding(8.dp))
            Text(stringResource(R.string.edit))
        }
    }

    if(exam.custom) {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), contentAlignment = Alignment.BottomStart
        ) {
            FloatingActionButton(
                onClick = { showDeleteDialog = true },
                containerColor = MaterialTheme.colorScheme.errorContainer
            ) {
                Icon(rememberDelete(), contentDescription = null)
            }
        }
    }

    if(showDeleteDialog) {
        DeleteDialog(onDelete = onDelete, onClose = { showDeleteDialog = false }, textId = R.string.delete_entry)
    }

    if(showEditDialog) {
        EditExamDialog(exam = exam, onClose = { showEditDialog = false }, onEdit = onEdit)
    }
}