package com.nefidov.kotlin_mvvm_application.features.paging.presentation.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.unit.dp
import com.nefidov.kotlin_mvvm_application.features.paging.data.models.UnsplashImage
import com.nefidov.kotlin_mvvm_application.features.paging.presentation.viewModel.UnsplashViewModel
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun UnsplashScreen() {
    val viewModel = koinViewModel<UnsplashViewModel>()

    val images = viewModel.imagesState.collectAsLazyPagingItems()

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(count = images.itemCount) { index ->
            val item = images[index]!!
            UnsplashImageItem(image = item)
        }
    }
}

@Composable
fun UnsplashImageItem(image: UnsplashImage) {
    Column(modifier = Modifier.fillMaxWidth()) {
        AsyncImage(
            model = image.urls.regular,
            contentDescription = image.description,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        Text(text = image.description ?: "No description", maxLines = 1)
    }
}