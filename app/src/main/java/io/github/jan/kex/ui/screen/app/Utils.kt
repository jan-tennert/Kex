package io.github.jan.kex.ui.screen.app

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import io.github.jan.kex.ui.nav.NavigationTarget

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.animatedComposable(destination: String, content: @Composable () -> Unit) {
    composable(
        destination,
        enterTransition = {
            val target = NavigationTarget.Main.entries.indexOfFirst { it.destination == this.targetState.destination.route }
            val initial = NavigationTarget.Main.entries.indexOfFirst { it.destination == this.initialState.destination.route }
            val direction = if (target > initial) {
                AnimatedContentTransitionScope.SlideDirection.Left
            } else {
                AnimatedContentTransitionScope.SlideDirection.Right
            }
            if(initial == -1) {
                fadeIn()
            } else {
                slideIntoContainer(
                    towards = direction,
                    animationSpec = tween(
                        durationMillis = 200,
                        easing = LinearEasing // interpolator
                    )
                )
            }
        },
        exitTransition = {
            val target = NavigationTarget.Main.entries.indexOfFirst { it.destination == this.targetState.destination.route }
            val initial = NavigationTarget.Main.entries.indexOfFirst { it.destination == this.initialState.destination.route }
            val direction = if (target > initial) {
                AnimatedContentTransitionScope.SlideDirection.Left
            } else {
                AnimatedContentTransitionScope.SlideDirection.Right
            }
            if(target == -1) {
                fadeOut()
            } else {
                slideOutOfContainer(
                    towards = direction,
                    animationSpec = tween(
                        durationMillis = 200,
                        easing = LinearEasing
                    )
                )
            }
        }
    ) {
        content()
    }
}