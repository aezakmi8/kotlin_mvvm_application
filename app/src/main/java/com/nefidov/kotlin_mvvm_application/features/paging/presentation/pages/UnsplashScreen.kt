package com.nefidov.kotlin_mvvm_application.features.paging.presentation.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.nefidov.kotlin_mvvm_application.features.paging.data.models.UnsplashImage
import com.nefidov.kotlin_mvvm_application.features.paging.presentation.viewModel.UnsplashViewModel
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun UnsplashScreen(viewModel: UnsplashViewModel = koinViewModel()) {
    val images = viewModel.imagesState.collectAsLazyPagingItems()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()

    Box(modifier = Modifier.fillMaxSize()) {
        if (isLoading || images.loadState.refresh is LoadState.Loading) {
            Loading()
        }
        else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
//                item {
//                    Button(
//                        onClick = {
//                            viewModel.setLoading()
//                        },
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(vertical = 16.dp)
//                    ) {
//                        Text(text = "SetLoading")
//                    }
//                }
                items(count = images.itemCount) { index ->
                    val item = images[index]!!
                    UnsplashImageItem(
                        image = item
                    )
                }

                item {
                    if (images.loadState.append is LoadState.Loading) {
                        Loading()
                    }
                }
            }
        }
    }
}

@Composable
fun Loading() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun UnsplashImageItem(
    image: UnsplashImage
) {
    Column(modifier = Modifier) {
        AsyncImage(
            model = image.urls.regular,
            contentDescription = image.description,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = image.description ?: "No description", maxLines = 1)
    }
}

