package io.github.jan.kex.ui.screen.app

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import io.github.jan.kex.ui.icons.rememberDownloadIcon
import io.github.jan.kex.ui.nav.NavigationTarget

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(currentTarget: NavigationTarget?, navController: NavController) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = currentTarget?.labelId?.let { stringResource(it) } ?: ""
            )
        },
        actions = {
            if(currentTarget == NavigationTarget.Exams) {
                IconButton(onClick = { navController.navigate(NavigationTarget.Exams.Import.destinationFormat) }) {
                    Icon(rememberDownloadIcon(), contentDescription = "Import")
                }
            }
        }
    )
}