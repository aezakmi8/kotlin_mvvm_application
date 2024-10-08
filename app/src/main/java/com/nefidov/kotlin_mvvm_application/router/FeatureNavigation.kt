package com.nefidov.kotlin_mvvm_application.router

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.nefidov.kotlin_mvvm_application.features.main.presentation.pages.MainScreen
import com.nefidov.kotlin_mvvm_application.features.main.presentation.pages.OtherListScreen
import com.nefidov.kotlin_mvvm_application.features.paging.presentation.pages.UnsplashScreen

@Composable
fun FeatureNavigation(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = MainRoute,
    ) {
        composable<MainRoute> {
            MainScreen(
                onOthersListClick = {
                    navController.navigate(OtherListRoute)
                },
                onImagesListClick = {
                    navController.navigate(PagingRoute)
                }
            )
        }

        composable<OtherListRoute> {
            OtherListScreen()
        }

        composable<PagingRoute> {
            UnsplashScreen()
        }

        composable<OtherDetailRoute> {
            it.toRoute<OtherDetailRoute>()
        }
    }
}