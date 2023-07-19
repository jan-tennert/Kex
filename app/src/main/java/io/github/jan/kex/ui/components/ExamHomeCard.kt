package io.github.jan.kex.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.jan.kex.data.remote.Exam

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExamHomeCard(exam: Exam, modifier: Modifier = Modifier, onClick: () -> Unit) {
    ElevatedCard(onClick, modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(12.dp)
        ) {
            Text(exam.subject)
            Spacer(Modifier.weight(1f))
        }
    }
}