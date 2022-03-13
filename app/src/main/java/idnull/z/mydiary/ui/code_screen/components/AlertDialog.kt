package idnull.z.mydiary.ui.code_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun ShowAlertDialog(textMain: String, textTitle: String, showState: Boolean, onClick: () -> Unit) {
    if (showState) {
        AlertDialog(
            onDismissRequest = {
                onClick()
            },
            text = { Text(text = textMain) },
            title = { Text(text = textTitle) },
            buttons = {
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Close",
                        modifier = Modifier
                            .padding(24.dp)
                            .clickable {
                                onClick()
                            }
                    )
                }
            },

            )
    }
}

