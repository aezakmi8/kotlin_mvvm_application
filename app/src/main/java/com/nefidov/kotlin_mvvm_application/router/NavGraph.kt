package com.nefidov.kotlin_mvvm_application.router

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nefidov.kotlin_mvvm_application.features.main.presentation.pages.MainScreen
import com.nefidov.kotlin_mvvm_application.features.main.presentation.pages.OtherListScreen

@Composable
fun NavGraph (
    navController: NavHostController,
    innerPadding: PaddingValues,
    // maybe add authGuard delegate
) {
    NavHost(
        navController = navController,
        startDestination = MainRoute,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable<MainRoute> {
            MainScreen(onOthersListClick = {
                navController.navigate(OtherListRoute)
            })
        }
        composable<OtherListRoute> {
            OtherListScreen()
        }
//        composable<OtherDetailRoute> {
//            val arguments = it.toRoute<OtherDetailRoute>()
//
//        }
    }
}