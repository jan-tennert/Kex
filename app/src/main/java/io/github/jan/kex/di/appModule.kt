package io.github.jan.kex.di

import io.github.jan.kex.vm.AuthenticationViewModel
import io.github.jan.kex.vm.ExamViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        AuthenticationViewModel(get(), get())
    }
    viewModel {
        ExamViewModel(get(), get())
    }
}