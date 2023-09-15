package io.github.jan.kex.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.jan.kex.BuildConfig
import io.github.jan.kex.R
import io.github.jan.kex.data.remote.GithubApi
import io.github.jan.kex.data.remote.UpdateDownloadEvent
import io.github.jan.kex.data.remote.UpdateManager
import io.github.z4kn4fein.semver.Version
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class UpdateViewModel(
    private val githubApi: GithubApi,
    private val updateManager: UpdateManager
): ViewModel() {

    val latestVersion = MutableStateFlow<Version?>(null)
    val versionDialogResult = MutableStateFlow<Int?>(null)
    val downloadStatus = MutableStateFlow<UpdateDownloadEvent?>(null)
    val ignoreUpdate = MutableStateFlow(false)

    fun checkForUpdates(silent: Boolean) {
        viewModelScope.launch {
            runCatching {
                githubApi.retrieveLatestVersion()
            }.onSuccess {
                latestVersion.value = it
            }.onFailure {
                it.printStackTrace()
                latestVersion.value = null
                if(!silent) {
                    versionDialogResult.value = R.string.version_check_network_failure
                }
            }
            if(!silent && latestVersion.value.toString() == BuildConfig.VERSION_NAME) {
                versionDialogResult.value = R.string.newest_version_installed
            }
        }
    }

    fun downloadLatestVersion() {
        viewModelScope.launch {
            runCatching {
                updateManager.downloadLatestVersion(latestVersion.value ?: return@launch).onEach {
                    downloadStatus.value = it
                }.launchIn(viewModelScope)
            }.onFailure {
                downloadStatus.value = null
            }
        }
    }

    fun installLatestVersion() {
        downloadStatus.value = null
        ignoreUpdate.value = true
        viewModelScope.launch {
            runCatching {
                updateManager.installLatestVersion()
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

}