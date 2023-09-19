package io.github.jan.kex.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.jan.kex.data.remote.Subject
import io.github.jan.kex.data.remote.Task
import kotlinx.datetime.Clock

object SubjectCardDefaults {

    val TABLET_SIZE = 200.dp
    val PHONE_SIZE = 150.dp

}

@Composable
fun SubjectCard(
    subject: Subject,
    modifier: Modifier = Modifier
) {
    ElevatedCard(modifier) {
        Box {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    subject.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

val Task.daysUntil get(): Long = (dueDate - Clock.System.now()).inWholeDays + 1