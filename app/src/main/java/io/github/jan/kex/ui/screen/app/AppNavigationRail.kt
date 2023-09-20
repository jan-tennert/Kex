package io.github.jan.kex.ui.screen.app

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.runtime.Composable
import io.github.jan.kex.ui.nav.NavigationTarget

//For tablets
@Composable
fun AppNavigationRail(
    currentDestination: String?,
    navigate: (String) -> Unit
) {
    NavigationRail {
        NavigationTarget.Main.entries.forEach {
            NavigationRailItem(
                selected = currentDestination == it.destination,
                onClick = { if(currentDestination != it.destination) navigate(it.destination) },
                icon = {
                    Icon(
                        imageVector = it.icon,
                        contentDescription = null
                    )
                })
        }
    }
}