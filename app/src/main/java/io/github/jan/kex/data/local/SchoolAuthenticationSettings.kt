package io.github.jan.kex.data.local

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.Settings
import com.russhwolf.settings.coroutines.getStringFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

interface SchoolAuthenticationSettings {

    val username: Flow<String>
    val password: Flow<String>

    suspend fun setUsername(username: String)

    suspend fun setPassword(password: String)

    suspend fun reset()

}

@OptIn(ExperimentalSettingsApi::class)
internal class SchoolAuthenticationSettingsImpl(
    private val settings: ObservableSettings
): SchoolAuthenticationSettings {

    override val username: Flow<String> = settings.getStringFlow("username", "")
    override val password: Flow<String> = settings.getStringFlow("password", "")

    override suspend fun setUsername(username: String) {
        withContext(Dispatchers.IO) {
            settings.putString("username", username)
        }
    }

    override suspend fun setPassword(password: String) {
        withContext(Dispatchers.IO) {
            settings.putString("password", password)
        }
    }

    override suspend fun reset() {
        withContext(Dispatchers.IO) {
            settings.remove("username")
            settings.remove("password")
        }
    }

}