package io.github.jan.kex.ui.screen.settings.entries

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.github.jan.kex.BuildConfig
import io.github.jan.kex.R
import io.github.jan.kex.vm.UpdateViewModel

@Composable
fun InformationEntry(updateVm: UpdateViewModel) {
    Column {
        Text(stringResource(R.string.current_version, BuildConfig.VERSION_NAME))
        Spacer(modifier = Modifier.height(6.dp))
        Button(onClick = { updateVm.ignoreUpdate.value = false; updateVm.checkForUpdates(false) }) {
            Text(stringResource(R.string.check_for_updates))
        }
    }
}