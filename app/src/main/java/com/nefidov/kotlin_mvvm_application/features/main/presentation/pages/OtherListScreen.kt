package com.nefidov.kotlin_mvvm_application.features.main.presentation.pages

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun OtherListScreen(modifier: Modifier = Modifier) {
    val items = remember { listOf("Item 1", "Item 2", "Item 3", "Item 4") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(items) { item: String ->
            Text(
                text = item,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            )
        }
    }
}
