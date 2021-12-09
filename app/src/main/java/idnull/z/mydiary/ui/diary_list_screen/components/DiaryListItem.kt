package idnull.z.mydiary.ui.diary_list_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import idnull.z.mydiary.domain.Diary
import idnull.z.mydiary.domain.DiaryUnit
import idnull.z.mydiary.utils.convertData


@Composable
fun DiaryListItem(diary: Diary, modifier: Modifier = Modifier) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp),
        shape = RoundedCornerShape(6.dp),
        elevation = 6.dp,
        backgroundColor = MaterialTheme.colors.background,

        ) {
        Column(
            modifier = modifier
                .fillMaxSize()
        ) {
            Text(
                modifier = Modifier.padding(start = 16.dp, top = 18.dp, bottom = 24.dp),
                color = MaterialTheme.colors.onBackground,
                text = diary.date,
                fontSize = 17.sp
            )
            Text(
                color = MaterialTheme.colors.onBackground,
                text = diary.title,
                fontSize = 17.sp,
                modifier = Modifier.padding(start = 16.dp, bottom = 2.dp)
            )
            Text(
                color = MaterialTheme.colors.onBackground,
                text = diary.content,
                maxLines = 4,
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
            )
        }

    }

}


