package io.github.jan.kex.ui.screen.app

import android.net.ConnectivityManager
import android.net.Network
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import io.github.jan.kex.vm.AuthenticationViewModel
import io.github.jan.kex.vm.ExamViewModel
import io.github.jan.kex.vm.SubjectViewModel
import io.github.jan.kex.vm.TaskViewModel
import org.koin.androidx.compose.getViewModel


@Composable
fun AutoRefresh(
    examVm: ExamViewModel = getViewModel(),
    authVm: AuthenticationViewModel = getViewModel(),
    subjectVm: SubjectViewModel = getViewModel(),
    taskVm: TaskViewModel = getViewModel()
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    DisposableEffect(lifecycleOwner, context) {
        val connectivityManager = context.getSystemService(ConnectivityManager::class.java)
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                //   examVm.syncExams(authVm.schoolUsername.value, authVm.schoolPassword.value)
                subjectVm.refreshSubjects()

                taskVm.syncTasks()
            }
        }

        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                taskVm.syncTasks()
                examVm.syncExams(authVm.schoolUsername.value, authVm.schoolPassword.value)
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)
        connectivityManager?.registerDefaultNetworkCallback(networkCallback)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            connectivityManager?.unregisterNetworkCallback(networkCallback)
        }
    }
}