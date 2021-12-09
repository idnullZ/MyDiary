package idnull.z.mydiary.ui.add_edit_screen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@Composable
fun ControlTextField(
    text: String,
    hint: String,
    maxLines: Int = Int.MAX_VALUE,
    modifier: Modifier = Modifier,
    isHintVisible: Boolean = true,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle = TextStyle(),
    singleLine: Boolean = false,
    onFocusChange: (FocusState) -> Unit,

    ) {

    Box(
        modifier = modifier
    ) {
        BasicTextField(
            maxLines = maxLines,
            value = text,
            onValueChange = onValueChange,
            singleLine = singleLine,
            textStyle = textStyle,
            modifier = Modifier
                .onFocusChanged {
                    onFocusChange(it)
                },


            )
        if (isHintVisible) {
            Text(text = hint, style = textStyle, color = Color(0xFF68838F))
        }
    }


}