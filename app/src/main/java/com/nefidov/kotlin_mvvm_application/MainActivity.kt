package com.nefidov.kotlin_mvvm_application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.mutableStateOf
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.rememberNavController
import com.nefidov.kotlin_mvvm_application.common.data.tokenProvider.TokenProvider
import com.nefidov.kotlin_mvvm_application.common.data.tokenProvider.models.AuthState
import com.nefidov.kotlin_mvvm_application.router.AuthNavigation
import com.nefidov.kotlin_mvvm_application.router.FeatureNavigation
import com.nefidov.kotlin_mvvm_application.ui.theme.Kotlin_mvvm_applicationTheme
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class MainActivity : ComponentActivity(), KoinComponent {

    private val tokenProvider: TokenProvider by inject()
    private var userAuthState = mutableStateOf(AuthState.Unknown)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                tokenProvider.isAuthenticated.collect {
                    userAuthState.value = it
                }
            }
        }

        setContent {
            Kotlin_mvvm_applicationTheme {

                val splash = installSplashScreen()

                splash.setKeepOnScreenCondition {
                    userAuthState.value == AuthState.Unknown
                }

                val navController = rememberNavController()

                when (userAuthState.value) {
                    AuthState.Authenticated -> FeatureNavigation(
                        navController
                    )

                    AuthState.NotAuthenticated -> AuthNavigation(
                        navController
                    )

                    AuthState.Unknown -> {}
                }
            }
        }
    }
}