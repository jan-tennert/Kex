package io.github.jan.kex.data.local

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

interface SubjectSuggestionDataSource {

    fun getSuggestionsAsFlow(): Flow<List<String>>

    suspend fun insert(name: String)

    suspend fun insertAll(names: List<String>)

    suspend fun clear()

}

internal class SubjectSuggestionDataSourceImpl(
    appDatabase: AppDatabase 
): SubjectSuggestionDataSource {

    private val queries = appDatabase.subjectSuggestionQueries

    override fun getSuggestionsAsFlow(): Flow<List<String>> {
        return queries.selectAll().asFlow().mapToList(Dispatchers.Default)
    }

    override suspend fun insert(name: String) {
        withContext(Dispatchers.IO) {
            queries.insert(name)
        }
    }

    override suspend fun insertAll(names: List<String>) {
        withContext(Dispatchers.IO) {
            queries.transaction {
                names.forEach { queries.insert(it) }
            }
        }
    }

    override suspend fun clear() {
        withContext(Dispatchers.IO) {
            queries.deleteAll()
        }
    }

}