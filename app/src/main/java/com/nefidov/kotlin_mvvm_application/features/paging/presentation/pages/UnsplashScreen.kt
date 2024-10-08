package com.nefidov.kotlin_mvvm_application.features.paging.presentation.pages

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.nefidov.kotlin_mvvm_application.common.presentation.widgets.CustomAsyncImage
import com.nefidov.kotlin_mvvm_application.features.paging.domain.entities.UnsplashImage
import com.nefidov.kotlin_mvvm_application.features.paging.presentation.viewModel.UnsplashViewModel
import org.koin.compose.viewmodel.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnsplashScreen(viewModel: UnsplashViewModel = koinViewModel()) {
    val images = viewModel.imagesState.collectAsLazyPagingItems()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val isRefreshing = images.loadState.refresh is LoadState.Loading
    val refreshState = rememberPullToRefreshState()

    val onRefresh: () -> Unit = {
        viewModel.refresh()
    }

    val scaleFraction = {
        if (isRefreshing) 1f
        else LinearOutSlowInEasing.transform(refreshState.distanceFraction).coerceIn(0f, 1f)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullToRefresh(
                isRefreshing = isRefreshing,
                state = refreshState,
                onRefresh = onRefresh
            )
    ) {
        if (isLoading) {
            Loading()
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // test button
                item {
                    Button(
                        onClick = {
                            viewModel.setLoading()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                    ) {
                        Text(text = "SetLoading")
                    }
                }

                // items
                items(
                    count = images.itemCount,
                    key = {
                        images[it]!!.uuid
                    }
                ) { index ->
                    val item = images[index]!!
                    UnsplashImageItem(
                        image = item
                    )
                }

                // footer loading indicator
                item {
                    if (images.loadState.append is LoadState.Loading) {
                        Loading()
                    }
                }
            }

            // refresh indicator
            Box(
                Modifier
                    .align(Alignment.TopCenter)
                    .graphicsLayer {
                        scaleX = scaleFraction()
                        scaleY = scaleFraction()
                    }
            ) {
                PullToRefreshDefaults.Indicator(
                    state = refreshState,
                    isRefreshing = isRefreshing
                )
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
    image: UnsplashImage,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = CardDefaults.elevatedShape,
        elevation = CardDefaults.cardElevation(),
    ) {
        CustomAsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 200.dp),
            imgUrl = image.urls.small,
            key = image.uuid,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = image.description ?: "No description",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            maxLines = 1
        )
    }
}

