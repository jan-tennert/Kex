package io.github.jan.kex.ui.screen.subjects

import android.app.Activity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.*
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import io.github.jan.kex.R
import io.github.jan.kex.data.remote.Subject
import io.github.jan.kex.ui.components.SubjectCard
import io.github.jan.kex.ui.components.SubjectCardDefaults
import io.github.jan.kex.ui.dialog.SubjectCreateDialog
import io.github.jan.kex.ui.icons.rememberAddIcon
import io.github.jan.kex.ui.nav.NavigationTarget
import io.github.jan.kex.vm.SubjectViewModel
import io.github.jan.kex.vm.TaskViewModel

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3WindowSizeClassApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
@Suppress("Deprecation") //The alternative is not yet compatible with Material 3
fun SubjectScreen(subjectVm: SubjectViewModel, taskViewModel: TaskViewModel, navController: NavController) {
    val subjectsRefreshing by subjectVm.refreshing.collectAsStateWithLifecycle()
    val tasksRefreshing by taskViewModel.refreshing.collectAsStateWithLifecycle()

    val refreshing = subjectsRefreshing || tasksRefreshing
    var refreshStarted by remember { mutableStateOf(false) }
    val subjects by subjectVm.subjects.collectAsStateWithLifecycle(emptyList())

    var showSubjectCreateDialog by remember { mutableStateOf(false) }
    var showSubjectEditDialog by remember { mutableStateOf<Subject?>(null) }
    val context = LocalContext.current

    val windowSizeClass = calculateWindowSizeClass(context as Activity)
    val subjectCardSize = if(windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact) SubjectCardDefaults.PHONE_SIZE else SubjectCardDefaults.TABLET_SIZE

    val gridState = rememberLazyGridState()

    val expandCreateButton by remember { derivedStateOf { gridState.firstVisibleItemIndex == 0 } }

    var isRefreshing by remember { mutableStateOf(false) }
    if(refreshing) {
        LaunchedEffect(true) {
            refreshStarted = true
        }
    }
    if(refreshStarted && !refreshing) {
        LaunchedEffect(true) {
            isRefreshing = false
        }
    }
    val errorMessage by subjectVm.errorMessage.collectAsStateWithLifecycle()
    PullToRefreshBox(
        onRefresh = {
            isRefreshing = true
            subjectVm.refreshSubjects(); taskViewModel.syncTasks()
        },
        isRefreshing = isRefreshing
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // TopBar(showPastExams = showPastExams, onShowPastExamsChange = { examVm.showPastExams.value = !showPastExams })
            LazyVerticalGrid(
                columns = GridCells.Adaptive(subjectCardSize),
                modifier = Modifier.fillMaxSize(),
                state = gridState
            ) {
                items(subjects, { it.id }) {
                    SubjectCard(
                        subject = it,
                        modifier = Modifier
                            .size(subjectCardSize)
                            .padding(8.dp)
                            .combinedClickable(
                                onLongClick = {
                                    showSubjectEditDialog = it
                                },
                            ) {
                                navController.navigate(
                                    NavigationTarget.Subjects.Detail.destinationFormat.format(
                                        it.id
                                    )
                                )
                            }
                            .animateItemPlacement()
                    )
                }
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
            Spacer(modifier = Modifier.weight(1f))
            ExtendedFloatingActionButton(
                onClick = { showSubjectCreateDialog = true },
                text = { Text(stringResource(R.string.create)) },
                icon = { Icon(rememberAddIcon(), contentDescription = null) },
                expanded = expandCreateButton,
            )
        }
    }

    if(showSubjectCreateDialog) {
        SubjectCreateDialog(
            oldSubject = null,
            onClose = { showSubjectCreateDialog = false },
            onCreate = subjectVm::createSubject
        )
    }

    if(showSubjectEditDialog != null) {
        SubjectCreateDialog(
            oldSubject = showSubjectEditDialog!!.name,
            onClose = { showSubjectEditDialog = null },
            onCreate = {
                subjectVm.updateSubject(showSubjectEditDialog!!, it)
            }
        )
    }

    if(errorMessage != null) {
        AlertDialog(
            onDismissRequest = { subjectVm.errorMessage.value = null },
            text = { Text(stringResource(errorMessage!!)) },
            confirmButton = {
                TextButton(onClick = { subjectVm.errorMessage.value = null }) {
                    Text("Ok")
                }
            }
        )
    }
}