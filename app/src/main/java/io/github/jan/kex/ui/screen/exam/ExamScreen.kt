package io.github.jan.kex.ui.screen.exam

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.*
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import io.github.jan.kex.R
import io.github.jan.kex.data.remote.Exam
import io.github.jan.kex.ui.components.ExamCard
import io.github.jan.kex.ui.components.ExamCardDefaults
import io.github.jan.kex.ui.dialog.DeleteDialog
import io.github.jan.kex.ui.icons.rememberAddIcon
import io.github.jan.kex.ui.icons.rememberDelete
import io.github.jan.kex.ui.icons.rememberDeselect
import io.github.jan.kex.ui.icons.rememberSelectAll
import io.github.jan.kex.ui.nav.NavigationTarget
import io.github.jan.kex.vm.AuthenticationViewModel
import io.github.jan.kex.vm.ExamViewModel
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
@Suppress("Deprecation") //The alternative is not yet compatible with Material 3
fun ExamScreen(
    examVm: ExamViewModel = getViewModel(),
    authVm: AuthenticationViewModel = getViewModel(),
    navController: NavController
) {
    val showPastExams by examVm.showPastExams.collectAsStateWithLifecycle()
    val filteredExams by examVm.filteredExams.collectAsStateWithLifecycle(emptyList())

    val isLoading by examVm.isLoading.collectAsStateWithLifecycle()
    var refreshStarted by remember { mutableStateOf(false) }
    val swipeRefreshState = rememberPullToRefreshState()


    val username by authVm.schoolUsername.collectAsStateWithLifecycle()
    val password by authVm.schoolPassword.collectAsStateWithLifecycle()

    if(swipeRefreshState.isRefreshing) {
        LaunchedEffect(true) {
            examVm.syncExams(username, password)
        }
    }
    if(isLoading) {
        LaunchedEffect(true) {
            refreshStarted = true
        }
    }
    if(refreshStarted && !isLoading) {
        LaunchedEffect(true) {
            swipeRefreshState.endRefresh()
        }
    }

    val selectedExams = remember { mutableStateListOf<Exam>() }

    var showDeleteDialog by remember { mutableStateOf(false) }

    val error: Int? by examVm.error.collectAsStateWithLifecycle()
    val gridState = rememberLazyGridState()
    val expandCreateButton by remember { derivedStateOf { gridState.firstVisibleItemIndex == 0 } }
    Box(Modifier.nestedScroll(swipeRefreshState.nestedScrollConnection)) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // TopBar(showPastExams = showPastExams, onShowPastExamsChange = { examVm.showPastExams.value = !showPastExams })
            LazyVerticalGrid(
                columns = GridCells.Adaptive(ExamCardDefaults.SIZE),
                modifier = Modifier.fillMaxSize(),
                state = gridState
            ) {
                items(filteredExams, { it.id }) {
                    ExamCard(
                        exam = it,
                        selected = it in selectedExams,
                        modifier = Modifier
                            .size(ExamCardDefaults.SIZE)
                            .padding(8.dp)
                            .combinedClickable(
                                onLongClick = {
                                    if (it in selectedExams) selectedExams.remove(it) else selectedExams.add(
                                        it
                                    )
                                },
                                onClick = {
                                    if (selectedExams.isNotEmpty()) {
                                        if (it in selectedExams) selectedExams.remove(it) else selectedExams.add(
                                            it
                                        )
                                    } else {
                                        navController.navigate(
                                            NavigationTarget.Exams.Detail.destinationFormat.format(
                                                it.id
                                            )
                                        )
                                    }
                                }
                            )
                            .animateItemPlacement()
                    )
                }
            }
        }
        PullToRefreshContainer(state = swipeRefreshState, modifier = Modifier.align(Alignment.TopCenter))
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
            if (selectedExams.isNotEmpty()) {
                FloatingActionButton(
                    onClick = { showDeleteDialog = true },
                    containerColor = MaterialTheme.colorScheme.errorContainer
                ) {
                    Icon(rememberDelete(), contentDescription = null)
                }
                Spacer(Modifier.weight(1f))
                FloatingActionButton(
                    onClick = {
                        if (selectedExams.size == filteredExams.size) {
                            selectedExams.clear()
                        } else {
                            selectedExams.clear()
                            selectedExams.addAll(filteredExams)
                        }
                    }
                ) {
                    Icon(
                        if (selectedExams.size == filteredExams.size) rememberDeselect() else rememberSelectAll(),
                        contentDescription = null
                    )
                }
            } else {
                Switch(
                    checked = showPastExams,
                    onCheckedChange = { examVm.showPastExams.value = !showPastExams })
                Spacer(modifier = Modifier.size(12.dp))
                Text(
                    stringResource(R.string.past_exams),
                    modifier = Modifier.weight(1f),
                    style = TextStyle(shadow = Shadow(MaterialTheme.colorScheme.inverseOnSurface,  Offset(5.0f, 5.0f), 1f))
                )
                ExtendedFloatingActionButton(
                    onClick = { navController.navigate(NavigationTarget.Exams.Create.destinationFormat) },
                    text = { Text(stringResource(R.string.create)) },
                    icon = { Icon(rememberAddIcon(), contentDescription = null) },
                    expanded = expandCreateButton,
                )
            }
        }
    }

    if(showDeleteDialog) {
        DeleteDialog(
            textId = R.string.delete_entries,
            onClose = { showDeleteDialog = false },
            onDelete = {
                examVm.deleteExams(selectedExams.map { it.id })
                selectedExams.clear()
                showDeleteDialog = false
            }
        )
    }
    /*if(username == null && password == null && !ignoreSchoolLogin) { //ignore this dialog for now
        SchoolLoginDialog(
            login = { username, password ->
                authVm.setSchoolCredentials(username, password)
            },
            onDismiss = { authVm.ignoreSchoolLogin.value = true }
        )
    }*/

    if(error != null) {
        AlertDialog(
            onDismissRequest = { examVm.error.value = null },
            title = { Text(stringResource(R.string.error)) },
            text = { Text(stringResource(error!!)) },
            dismissButton = {
                TextButton(onClick = { examVm.error.value = null }) {
                    Text("Ok")
                }
            },
            confirmButton = {
                TextButton(onClick = { navController.navigate(NavigationTarget.Settings.destinationFormat); examVm.error.value = null }) {
                    Text(stringResource(R.string.switch_to_settings))
                }
            }
        )
    }
}
