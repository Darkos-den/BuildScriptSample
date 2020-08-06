package com.company.projectName.android.home.view

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.layout.fillMaxHeight
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.unit.dp
import com.company.projectName.android.home.state.HomeInfo

@Composable
fun Data(data: HomeInfo.Data) {
    Text(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
        text = data.text
    )
}