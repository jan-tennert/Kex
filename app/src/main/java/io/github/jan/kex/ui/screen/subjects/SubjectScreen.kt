package io.github.jan.kex.ui.screen.subjects

import android.app.Activity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import io.github.jan.kex.R
import io.github.jan.kex.ui.components.SubjectCard
import io.github.jan.kex.ui.components.SubjectCardDefaults
import io.github.jan.kex.ui.dialog.SubjectCreateDialog
import io.github.jan.kex.ui.icons.EditIcon
import io.github.jan.kex.ui.nav.NavigationTarget
import io.github.jan.kex.vm.SubjectViewModel
import io.github.jan.kex.vm.TaskViewModel

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
@Suppress("Deprecation") //The alternative is not yet compatible with Material 3
fun SubjectScreen(subjectVm: SubjectViewModel, taskViewModel: TaskViewModel, navController: NavController) {
    val subjectsRefreshing by subjectVm.refreshing.collectAsStateWithLifecycle()
    val tasksRefreshing by taskViewModel.refreshing.collectAsStateWithLifecycle()
    val refreshing = subjectsRefreshing || tasksRefreshing
    val swipeRefreshState = rememberSwipeRefreshState(refreshing)
    val subjects by subjectVm.subjects.collectAsStateWithLifecycle(emptyList())
    var showSubjectCreateDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val windowSizeClass = calculateWindowSizeClass(context as Activity)
    val subjectCardSize = if(windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact) SubjectCardDefaults.PHONE_SIZE else SubjectCardDefaults.TABLET_SIZE
    SwipeRefresh(
        modifier = Modifier.fillMaxSize(),
        state = swipeRefreshState,
        onRefresh = { subjectVm.refreshSubjects(); taskViewModel.refreshTasks() },
        indicator = { state, refreshTrigger ->
            SwipeRefreshIndicator(
                state = state,
                refreshTriggerDistance = refreshTrigger,
                backgroundColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // TopBar(showPastExams = showPastExams, onShowPastExamsChange = { examVm.showPastExams.value = !showPastExams })
            LazyVerticalGrid(
                GridCells.Adaptive(subjectCardSize), modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                items(subjects, { it.id }) {
                    SubjectCard(
                        subject = it,
                        modifier = Modifier
                            .size(subjectCardSize)
                            .padding(8.dp)
                            .clickable {
                                navController.navigate(NavigationTarget.Subjects.Detail.destinationFormat.format(it.id))
                            }
                            .animateItemPlacement()
                    )
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Spacer(modifier = Modifier.weight(1f))
                ExtendedFloatingActionButton(
                    onClick = { showSubjectCreateDialog = true },
                    text = { Text(stringResource(R.string.create)) },
                    icon = { Icon(EditIcon, contentDescription = null) },
                )
            }
        }
    }

    if(showSubjectCreateDialog) {
        SubjectCreateDialog(
            onClose = { showSubjectCreateDialog = false },
            onCreate = subjectVm::createSubject
        )
    }
}