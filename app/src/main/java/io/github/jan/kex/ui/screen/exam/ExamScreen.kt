package io.github.jan.kex.ui.screen.exam

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import io.github.jan.kex.R
import io.github.jan.kex.data.remote.Exam
import io.github.jan.kex.ui.components.ExamCard
import io.github.jan.kex.ui.dialog.DeleteDialog
import io.github.jan.kex.ui.dialog.SchoolLoginDialog
import io.github.jan.kex.ui.icons.EditIcon
import io.github.jan.kex.ui.icons.rememberDelete
import io.github.jan.kex.ui.icons.rememberDeselect
import io.github.jan.kex.ui.icons.rememberSelectAll
import io.github.jan.kex.ui.nav.NavigationTarget
import io.github.jan.kex.vm.AuthenticationViewModel
import io.github.jan.kex.vm.ExamViewModel
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
@Suppress("Deprecation") //The alternative is not yet compatible with Material 3
fun ExamScreen(
    examVm: ExamViewModel = getViewModel(),
    authVm: AuthenticationViewModel = getViewModel(),
    navController: NavController
) {
    val showPastExams by examVm.showPastExams.collectAsStateWithLifecycle()
    val filteredExams by examVm.filteredExams.collectAsStateWithLifecycle(emptyList())
    val ignoreSchoolLogin by authVm.ignoreSchoolLogin.collectAsStateWithLifecycle()
    
    val isLoading by examVm.isLoading.collectAsStateWithLifecycle()
    val swipeRefreshState = rememberSwipeRefreshState(isLoading)

    val username by authVm.schoolUsername.collectAsStateWithLifecycle("")
    val password by authVm.schoolPassword.collectAsStateWithLifecycle("")

    val selectedExams = remember { mutableStateListOf<Exam>() }

    var showDeleteDialog by remember { mutableStateOf(false) }

    val error: Int? by examVm.error.collectAsStateWithLifecycle()
    if((username.isBlank() || password.isBlank()) && !ignoreSchoolLogin) {
        SchoolLoginDialog(
            login = { username, password ->
                authVm.setSchoolCredentials(username, password)
            },
            onDismiss = { authVm.ignoreSchoolLogin.value = true }
        )
    } else {
        SwipeRefresh(
            modifier = Modifier.fillMaxSize(),
            state = swipeRefreshState,
            onRefresh = { examVm.refreshExams(username, password) },
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
                    GridCells.Adaptive(128.dp), modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                ) {
                    items(filteredExams, { it.id }) {
                        ExamCard(
                            exam = it,
                            selected = it in selectedExams,
                            showSelection = selectedExams.isNotEmpty(),
                            modifier = Modifier
                                .size(128.dp)
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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    if(selectedExams.isNotEmpty()) {
                        FloatingActionButton(
                            onClick = { showDeleteDialog = true },
                            containerColor = MaterialTheme.colorScheme.errorContainer
                        ) {
                            Icon(rememberDelete(), contentDescription = null)
                        }
                        Spacer(Modifier.weight(1f))
                        FloatingActionButton(
                            onClick = {
                                if(selectedExams.size == filteredExams.size) {
                                    selectedExams.clear()
                                } else {
                                    selectedExams.clear()
                                    selectedExams.addAll(filteredExams)
                                }
                            }
                        ) {
                            Icon(if(selectedExams.size == filteredExams.size) rememberDeselect() else rememberSelectAll(), contentDescription = null)
                        }
                    } else {
                        Switch(
                            checked = showPastExams,
                            onCheckedChange = { examVm.showPastExams.value = !showPastExams })
                        Spacer(modifier = Modifier.size(12.dp))
                        Text(stringResource(R.string.past_exams), modifier = Modifier.weight(1f))
                        ExtendedFloatingActionButton(
                            onClick = { navController.navigate(NavigationTarget.Exams.Create.destinationFormat) },
                            text = { Text(stringResource(R.string.create)) },
                            icon = { Icon(EditIcon, contentDescription = null) }
                        )
                    }
                }
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
                TextButton(onClick = { navController.navigate(NavigationTarget.Settings.destinationFormat) }) {
                    Text(stringResource(R.string.switch_to_settings))
                }
            }
        )
    }
}