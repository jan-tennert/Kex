package io.github.jan.kex.data.remote

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.FileProvider
import io.github.z4kn4fein.semver.Version
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.onDownload
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.withContext
import java.io.File

sealed interface UpdateDownloadEvent {

    data class Progress(val progress: Float): UpdateDownloadEvent

    data object Finished: UpdateDownloadEvent

}

sealed interface UpdateManager {

    suspend fun downloadLatestVersion(version: Version): Flow<UpdateDownloadEvent>

    suspend fun installLatestVersion(): Boolean

}

internal class UpdateManagerImpl(
    private val context: Context,
    private val httpClient: HttpClient,
): UpdateManager {

    override suspend fun downloadLatestVersion(version: Version): Flow<UpdateDownloadEvent> {
        return withContext(Dispatchers.IO) {
            val file = File(context.filesDir, "update.apk")
            if(file.exists()) {
                file.delete()
            }
            file.createNewFile()
            callbackFlow {
                val response = httpClient.get(API_URL.format(version.toString())) {
                    onDownload { bytesSentTotal, contentLength ->
                        val float = bytesSentTotal.toFloat() / (contentLength?.toFloat() ?: 1f)
                        trySend(UpdateDownloadEvent.Progress(if(float.isNaN()) 0f else float))
                    }
                }
                file.writeBytes(response.body())
                trySend(UpdateDownloadEvent.Finished)
                close()
            }
        }
    }

    override suspend fun installLatestVersion(): Boolean {
        val file = File(context.filesDir, "update.apk")
        if(!file.exists()) return false
        val intent = Intent(Intent.ACTION_VIEW)
        val downloadedApk = FileProvider.getUriForFile(
            context,
            context.applicationContext.packageName + ".provider",
            file
        )
        intent.setDataAndType(downloadedApk, "application/vnd.android.package-archive")
        val resInfoList: List<ResolveInfo> = context.packageManager
            .queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
        for (resolveInfo in resInfoList) {
            context.grantUriPermission(
                context.applicationContext.packageName.toString() + ".provider",
                downloadedApk,
                Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
        }
        intent.flags =
            Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION
        startActivity(context, intent, null)
        return true
    }

    companion object {
        const val API_URL = "https://github.com/jan-tennert/Kex/releases/download/%s/app-release.apk"
    }

}