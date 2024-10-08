package com.nefidov.kotlin_mvvm_application.router

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nefidov.kotlin_mvvm_application.features.auth.presentation.pages.LoginScreen

@Composable
fun AuthNavigation(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = LoginScreenRoute,
    ) {
        composable<LoginScreenRoute> {
            LoginScreen(
                onLogin = {
                    navController.popBackStack()
                }
            )
        }
    }
}