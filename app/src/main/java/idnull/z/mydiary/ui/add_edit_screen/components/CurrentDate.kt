package idnull.z.mydiary.ui.add_edit_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import idnull.z.mydiary.R
import idnull.z.mydiary.ui.shared_component.TextStd
import idnull.z.mydiary.ui.theme.Green
import idnull.z.mydiary.ui.theme.Grey

@Composable
fun CurrentDate(date: String, addMood: () -> Unit) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextStd(value = date, fontSize = 18)
            Icon(
                painter = painterResource(id = R.drawable.ic_add),
                contentDescription = "add",
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(24.dp)
                    .clickable {
                        addMood()
                    },
                tint = Green,
            )
        }
        Divider(
            color = Grey, modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
        )
    }
}