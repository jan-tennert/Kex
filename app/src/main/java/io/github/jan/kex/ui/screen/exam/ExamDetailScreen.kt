package io.github.jan.kex.ui.screen.exam

import android.os.Build
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ElevatedCard
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
import com.mohamedrejeb.richeditor.model.RichTextState
import com.mohamedrejeb.richeditor.ui.material3.RichText
import io.github.jan.kex.R
import io.github.jan.kex.data.remote.Exam
import io.github.jan.kex.localizedDateString
import io.github.jan.kex.ui.dialog.DeleteDialog
import io.github.jan.kex.ui.icons.EditIcon
import io.github.jan.kex.ui.icons.rememberDelete

@Composable
fun ExamDetailScreen(exam: Exam, onEdit: () -> Unit, onDelete: () -> Unit) {
    var showDeleteDialog by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val themeState = remember(exam) {
            exam.theme?.let {
                RichTextState().apply {
                    setHtml(it)
                }
            }
        }
        Text(exam.subject, fontSize = 40.sp)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Text(exam.date.localizedDateString, fontSize = 15.sp)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(stringResource(R.string.score), fontSize = 25.sp, fontWeight = FontWeight.Bold)
        Text(exam.points?.toString() ?: stringResource(R.string.no_score), fontSize = 20.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Text(stringResource(R.string.theme), fontSize = 25.sp, fontWeight = FontWeight.Bold)
        Box(Modifier.weight(1f)) {
            if (themeState != null) {
                ElevatedCard(Modifier.verticalScroll(rememberScrollState()).fillMaxSize().padding(8.dp)) {
                    RichText(
                        state = themeState,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp),
                        fontSize = 20.sp,
                    )
                }
            } else {
                Text(stringResource(R.string.no_theme), fontSize = 20.sp)
            }
        }


    }
    Box(
        contentAlignment = Alignment.BottomStart,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            FloatingActionButton(
                onClick = { showDeleteDialog = true },
                containerColor = MaterialTheme.colorScheme.errorContainer
            ) {
                Icon(rememberDelete(), contentDescription = null)
            }
            Spacer(modifier = Modifier.weight(1f))
            ExtendedFloatingActionButton(
                onClick = { onEdit() },
                text = { Text(stringResource(R.string.edit)) },
                icon = { Icon(EditIcon, contentDescription = null) },
            )
        }
    }

    if (showDeleteDialog) {
        DeleteDialog(
            onDelete = onDelete,
            onClose = { showDeleteDialog = false },
            textId = R.string.delete_entry
        )
    }
}