package io.github.jan.kex.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.jan.kex.data.remote.ExamPlanEntry

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExamPlanCard(selected: Boolean, examPlanEntry: ExamPlanEntry, onSelect: () -> Unit) {
    Card(
        onSelect,
        modifier = Modifier
            .padding(6.dp),
        colors = if(selected) CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer) else CardDefaults.cardColors(),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Text(examPlanEntry.subject)
            Text(examPlanEntry.teacher)
        }
    }
}