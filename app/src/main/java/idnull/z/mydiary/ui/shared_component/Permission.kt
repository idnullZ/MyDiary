package idnull.z.mydiary.ui.shared_component


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionsRequired
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import idnull.z.mydiary.R

@Composable
@OptIn(ExperimentalPermissionsApi::class)
fun PermissionApp(
    permissions: List<String>,
    openSettings: () -> Unit,
    closeMessage: () -> Unit,
    content: @Composable () -> Unit

) {
    var isDenied by remember { mutableStateOf(false) }
    val permission = rememberMultiplePermissionsState(permissions = permissions)

    PermissionsRequired(
        multiplePermissionsState = permission,
        permissionsNotGrantedContent = {
            Scaffold(
                Modifier
                    .background(MaterialTheme.colors.surface)
                    .fillMaxSize()
            ) {
                isDenied = false
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_photo_camera),
                        contentDescription = "photo camera",
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1F)
                    )
                    TextStd(
                        value = "I need your permission to proceed further.",
                        fontSize = 24,
                        maxLine = 3
                    )
                    ButtonStd(text = "Grant permission") {
                        permission.launchMultiplePermissionRequest()
                    }
                    ButtonStd(text = "Exit") {

                        closeMessage()
                    }

                }
            }

        },
        permissionsNotAvailableContent = {
            isDenied = true
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(48.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_sad),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(128.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))
                TextStd(value = "Permission is denied. Please grant access in settings to use this app.")
                Spacer(modifier = Modifier.height(24.dp))
                ButtonStd(text = "Open Settings") {
                    openSettings()
                }
                Spacer(modifier = Modifier.height(24.dp))
                ButtonStd(text = "Close") {
                    closeMessage()
                }

            }
        }
    ) {
        content()
    }
}
