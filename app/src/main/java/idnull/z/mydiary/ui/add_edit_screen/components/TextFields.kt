package idnull.z.mydiary.ui.add_edit_screen.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import idnull.z.mydiary.ui.add_edit_screen.AddEditScreenEvent
import idnull.z.mydiary.ui.add_edit_screen.AddEditViewModel
import idnull.z.mydiary.ui.add_edit_screen.TextFieldState

@Composable
fun TextFields(
    titleState: TextFieldState,
    viewModel: AddEditViewModel,
    contentState: TextFieldState
) {
    /**  Title TextField */
    Spacer(modifier = Modifier.height(16.dp))
    ControlTextField(
        singleLine = true,
        maxLines = 1,
        text = titleState.text,
        hint = titleState.hint,
        onValueChange = {
            viewModel.obtainEvent(AddEditScreenEvent.EnteredTitle(it))
        },
        onFocusChange = {
            viewModel.obtainEvent(AddEditScreenEvent.ChangeTitleFocus(it))
        },
        isHintVisible = titleState.isHintVisible,
        textStyle = TextStyle(
            color = MaterialTheme.colors.onBackground,
            fontWeight = FontWeight.Normal,
            fontSize = 28.sp,
            letterSpacing = 0.sp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    )
    Spacer(modifier = Modifier.height(16.dp))

    /**  Content TextField */
    ControlTextField(
        text = contentState.text,
        hint = contentState.hint,
        onValueChange = {
            viewModel.obtainEvent(AddEditScreenEvent.EnteredContent(it))
        },
        onFocusChange = {
            viewModel.obtainEvent(AddEditScreenEvent.ChangeContentFocus(it))
        },
        isHintVisible = contentState.isHintVisible,
        textStyle = TextStyle(
            color = MaterialTheme.colors.onBackground,
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            letterSpacing = 0.5.sp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    )
}