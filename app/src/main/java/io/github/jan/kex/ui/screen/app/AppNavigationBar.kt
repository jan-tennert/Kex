package io.github.jan.kex.ui.screen.app

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import io.github.jan.kex.ui.nav.NavigationTarget

//For phones
@Composable
fun AppNavigationBar(
    currentDestination: String?,
    navigate: (String) -> Unit
) {
    NavigationBar {
        NavigationTarget.Main.entries.forEach {
            NavigationBarItem(
                selected = it.destination == currentDestination,
                onClick = { navigate(it.destination) },
                icon = {
                    Icon(
                        imageVector = it.icon,
                        contentDescription = null
                    )
                })
        }
    }
}