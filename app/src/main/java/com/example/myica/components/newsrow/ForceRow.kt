package com.example.myica.components.newsrow


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myica.models.Force


@Composable
fun ForceRow(
    modifier: Modifier = Modifier,
    force: Force,
) {

    val context = LocalContext.current
    Card(
        modifier = modifier
            .padding(5.dp)
            .fillMaxWidth()
            .clickable {
                //TO-DO
            }
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.Top)
                        .weight(1f)
                ) {
                    Text(
                        force.name.toString(),
                        modifier = Modifier,
                        style = MaterialTheme.typography.titleMedium,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 5
                    )
                }

            }
        }
    }
}
