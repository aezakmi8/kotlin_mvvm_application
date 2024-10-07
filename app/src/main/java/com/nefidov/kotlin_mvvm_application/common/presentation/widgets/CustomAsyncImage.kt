package com.nefidov.kotlin_mvvm_application.common.presentation.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.imageLoader
import coil.request.CachePolicy
import coil.request.ImageRequest

@Composable
fun CustomAsyncImage(
    key: String? = null,
    imgUrl: String,
    modifier: Modifier = Modifier
        .fillMaxWidth()
) {
    val context = LocalContext.current

    val request: ImageRequest = ImageRequest.Builder(context)
        .data(imgUrl)
        .crossfade(true)
        .diskCacheKey(key)
        .diskCachePolicy(CachePolicy.ENABLED)
        .build()

    context.imageLoader.enqueue(request)

    SubcomposeAsyncImage(
        modifier = modifier,
        model = request,
        contentDescription = null,
        contentScale = ContentScale.FillWidth
    ) {
        val state = painter.state
        if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
            Box(contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            SubcomposeAsyncImageContent(
                alignment = Alignment.Center
            )
        }
    }
}

@Preview
@Composable
fun Preview() {
    CustomAsyncImage(
        modifier = Modifier
            .width(200.dp)
            .height(200.dp)
            .background(Color.White),
        imgUrl = "https://images.unsplash.com/photo-1719937206109-7f4e933230c8?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w2NjEwNzJ8MXwxfGFsbHwxfHx8fHx8fHwxNzI4Mjk3MDQ3fA&ixlib=rb-4.0.3&q=80&w=400"
    )
}