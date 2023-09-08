package io.github.jan.kex.data.remote

import io.github.z4kn4fein.semver.Version
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive

sealed interface GithubApi {

    suspend fun retrieveLatestVersion(): Version?

}

internal class GithubApiImpl(
    private val httpClient: HttpClient
): GithubApi {

    override suspend fun retrieveLatestVersion(): Version? {
        val version = httpClient.get(API_URL)
            .body<JsonObject>()["tag_name"]?.jsonPrimitive?.content
        return version?.let { Version.parse(it) }
    }

    companion object {
        const val API_URL = "https://api.github.com/repos/jan-tennert/Kex/releases/latest"
    }

}