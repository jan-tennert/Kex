package io.github.jan.kex.di

import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.jan.kex.vm.AuthenticationViewModel
import io.github.jan.kex.vm.ExamViewModel
import io.github.jan.kex.vm.SubjectViewModel
import io.github.jan.kex.vm.TaskViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        AuthenticationViewModel(get(), get())
    }
    viewModel { 
        ExamViewModel(get(), get(), get())
    }
    viewModel {
        SubjectViewModel(get(), get())
    }
    viewModel {
        TaskViewModel(get(), get())
    }
}