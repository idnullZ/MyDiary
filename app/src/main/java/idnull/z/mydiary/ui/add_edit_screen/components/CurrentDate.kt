package idnull.z.mydiary.ui.add_edit_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import idnull.z.mydiary.ui.shared_component.TextStd
import idnull.z.mydiary.ui.theme.Grey

@Composable
fun CurrentDate(date:String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        
        TextStd(value = date, fontSize = 18)
        
        Divider( color = Grey, modifier = Modifier
            .fillMaxWidth()
            .height(1.dp))
    }
    Spacer(modifier = Modifier.height(16.dp))


}