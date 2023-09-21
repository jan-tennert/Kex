package io.github.jan.kex.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.jan.kex.data.remote.ExamPlan
import io.github.jan.kex.data.remote.ExamPlanApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ExamPlanViewModel(
    private val examPlanApi: ExamPlanApi
): ViewModel() {

    val examPlanKeys = MutableStateFlow(emptyList<String>())
    val examPlanKeysLoading = MutableStateFlow(false)
    val examPlan = MutableStateFlow<ExamPlan?>(null)
    val examPlanLoading = MutableStateFlow(false)

    fun updateExamPlansKeys() {
        examPlanKeysLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                examPlanApi.retrieveExamPlanKeys()
            }.onSuccess {
                examPlanKeys.value = it
            }.onFailure {
                it.printStackTrace()
            }
            examPlanKeysLoading.value = false
        }
    }

    fun updateExamPlan(key: String) {
        examPlanLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                examPlanApi.retrieveExamPlan(key)
            }.onSuccess {
                examPlan.value = it
            }.onFailure {
                it.printStackTrace()
            }
            examPlanLoading.value = false
        }
    }


}